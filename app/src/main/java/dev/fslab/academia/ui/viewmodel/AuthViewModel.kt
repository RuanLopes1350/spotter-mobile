package dev.fslab.academia.ui.viewmodel

import android.util.Base64
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.fslab.academia.model.*
import dev.fslab.academia.network.RetrofitClient
import com.google.gson.Gson
import dev.fslab.academia.network.CookieManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    data class Success(val user: User) : AuthState()
    data class Error(val message: String) : AuthState()
}

class AuthViewModel : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser.asStateFlow()

    // REMOVIDO: _accessToken — não existe mais token JWT no cliente

    // ── Verificar sessão existente (auto-login ao abrir o app) ──────
    fun checkSession() {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            try {
                val response = RetrofitClient.authApi.getSession()
                if (response.isSuccess() && response.user != null) {
                    val user = response.user.toUser()
                    _currentUser.value = user
                    _authState.value = AuthState.Success(user)
                } else {
                    _authState.value = AuthState.Idle // Sem sessão ativa
                }
            } catch (e: Exception) {
                _authState.value = AuthState.Idle // Sem sessão
            }
        }
    }

    // ── Login ───────────────────────────────────────────────────────
    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            try {
                val response = RetrofitClient.authApi.login(
                    LoginRequest(email = email, password = password)
                )

                if (response.isSuccess() && response.user != null) {
                    // Cookie de sessão já foi armazenado automaticamente pelo CookieJar.
                    // Basta ler os dados do usuário da resposta JSON.
                    val user = response.user.toUser()
                    _currentUser.value = user
                    _authState.value = AuthState.Success(user)
                } else {
                    _authState.value = AuthState.Error("Credenciais inválidas")
                }
            } catch (e: retrofit2.HttpException) {
                val msg = when (e.code()) {
                    401 -> "Email ou senha incorretos"
                    429 -> "Muitas tentativas. Aguarde um momento."
                    500 -> "Erro no servidor. Tente novamente mais tarde."
                    else -> "Erro: ${e.message()}"
                }
                _authState.value = AuthState.Error(msg)
            } catch (e: Exception) {
                _authState.value = AuthState.Error("Sem conexão com a internet")
            }
        }
    }

    // ── Logout ─────────────────────────────────────────────────────
    fun logout() {
        viewModelScope.launch {
            try {
                RetrofitClient.authApi.logout()  // Invalida sessão no servidor
            } catch (e: Exception) {
                // Ignora erro de rede no logout — limpa localmente de qualquer forma
            } finally {
                CookieManager.clearCookies()      // Remove cookie do dispositivo
                _currentUser.value = null
                _authState.value = AuthState.Idle
            }
        }
    }
}