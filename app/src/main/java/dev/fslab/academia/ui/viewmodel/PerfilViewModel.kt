package dev.fslab.academia.ui.viewmodel

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.fslab.academia.model.AlunoProfileData
import dev.fslab.academia.model.TreinadorProfileData
import dev.fslab.academia.model.UserTipo
import dev.fslab.academia.network.RetrofitClient
import dev.fslab.academia.ui.util.FileUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

sealed interface PerfilUiState {
    data object Idle : PerfilUiState
    data object Loading : PerfilUiState
    data class SuccessAluno(val profile: AlunoProfileData) : PerfilUiState
    data class SuccessTreinador(val profile: TreinadorProfileData) : PerfilUiState
    data class Error(val message: String) : PerfilUiState
}

class PerfilViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<PerfilUiState>(PerfilUiState.Idle)
    val uiState: StateFlow<PerfilUiState> = _uiState.asStateFlow()

    private val _isUpdating = MutableStateFlow(false)
    val isUpdating: StateFlow<Boolean> = _isUpdating.asStateFlow()

    /**
     * Carrega o perfil do usuário logado baseado no seu tipo.
     */
    fun carregarPerfil(tipo: UserTipo) {
        viewModelScope.launch {
            _uiState.value = PerfilUiState.Loading
            try {
                if (tipo == UserTipo.TREINADOR) {
                    val response = RetrofitClient.profileApi.getTreinadorProfile()
                    _uiState.value = PerfilUiState.SuccessTreinador(response.data)
                } else {
                    val response = RetrofitClient.profileApi.getAlunoProfile()
                    _uiState.value = PerfilUiState.SuccessAluno(response.data)
                }
            } catch (e: Exception) {
                _uiState.value = PerfilUiState.Error(e.message ?: "Erro ao carregar perfil")
            }
        }
    }

    /**
     * Atualiza o perfil do Aluno com suporte a foto opcional.
     */
    fun atualizarAluno(
        context: Context,
        id: String,
        nome: String,
        username: String?,
        dataNascimento: String,
        sexo: String,
        peso: Double?,
        altura: Int?,
        fotoUri: Uri? = null,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            _isUpdating.value = true
            try {
                val json = JSONObject().apply {
                    put("nome", nome)
                    put("username", username)
                    put("data_nascimento", dataNascimento)
                    put("sexo", sexo)
                    put("peso_atual", peso)
                    put("altura", altura)
                }

                val requestBody = json.toString().toRequestBody("application/json".toMediaTypeOrNull())
                val fotoPart = fotoUri?.let { FileUtils.createMultipartBody(context, it, "foto") }

                val response = RetrofitClient.profileApi.updateAlunoProfile(id, requestBody, fotoPart)
                if (response.success) {
                    _uiState.value = PerfilUiState.SuccessAluno(response.data)
                    onSuccess()
                }
            } catch (e: Exception) {
                // Log de erro ou notificação
            } finally {
                _isUpdating.value = false
            }
        }
    }
}
