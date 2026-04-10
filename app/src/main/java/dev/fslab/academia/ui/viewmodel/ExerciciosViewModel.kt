package dev.fslab.academia.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.fslab.academia.model.ExercicioData
import dev.fslab.academia.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.HttpException

sealed interface ExerciciosUiState {
    data object Idle : ExerciciosUiState
    data object Loading : ExerciciosUiState
    data object Empty : ExerciciosUiState
    data class Success(
        val exercicios: List<ExercicioData>,
        val total: Int,
        val page: Int,
    ) : ExerciciosUiState
    data class Error(val message: String) : ExerciciosUiState
}

class ExerciciosViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<ExerciciosUiState>(ExerciciosUiState.Idle)
    val uiState: StateFlow<ExerciciosUiState> = _uiState.asStateFlow()

    fun carregarExercicios(force: Boolean = false) {
        val estadoAtual = _uiState.value
        if (!force && estadoAtual is ExerciciosUiState.Loading) return

        viewModelScope.launch {
            _uiState.value = ExerciciosUiState.Loading
            try {
                val resposta = RetrofitClient.authApi.getExercicios()
                val exercicios = resposta.data?.dados.orEmpty()

                _uiState.value = if (exercicios.isEmpty()) {
                    ExerciciosUiState.Empty
                } else {
                    ExerciciosUiState.Success(
                        exercicios = exercicios,
                        total = resposta.data?.total ?: exercicios.size,
                        page = resposta.data?.page ?: 1,
                    )
                }
            } catch (e: HttpException) {
                val apiMessage = e.response()?.errorBody()?.string()?.let(::extractApiErrorMessage)
                _uiState.value = ExerciciosUiState.Error(apiMessage ?: mapHttpError(e.code()))
            } catch (_: Exception) {
                _uiState.value = ExerciciosUiState.Error("Sem conexao com a internet")
            }
        }
    }

    private fun mapHttpError(code: Int): String = when (code) {
        401 -> "Sessao expirada. Faca login novamente"
        403 -> "Voce nao possui permissao para visualizar exercicios"
        422 -> "Parametros invalidos para consulta de exercicios"
        in 500..599 -> "Servidor indisponivel no momento"
        else -> "Falha ao carregar exercicios. Codigo HTTP: $code"
    }

    private fun extractApiErrorMessage(rawBody: String): String? {
        return runCatching {
            val json = JSONObject(rawBody)
            when {
                json.has("message") -> json.getString("message")
                json.has("error") -> json.getString("error")
                else -> null
            }
        }.getOrNull()?.takeIf { it.isNotBlank() }
    }
}
