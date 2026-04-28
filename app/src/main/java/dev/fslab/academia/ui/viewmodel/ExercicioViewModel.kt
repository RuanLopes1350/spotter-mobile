package dev.fslab.academia.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.fslab.academia.model.EscopoExercicio
import dev.fslab.academia.model.ExercicioData
import dev.fslab.academia.model.GrupoMuscular
import dev.fslab.academia.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.HttpException

sealed interface ExercicioListUiState {
    data object Idle : ExercicioListUiState
    data object Loading : ExercicioListUiState
    data object Empty : ExercicioListUiState
    data class Success(
        val exercicios: List<ExercicioData>,
        val total: Int,
        val page: Int,
        val totalPages: Int
    ) : ExercicioListUiState
    data class Error(val message: String) : ExercicioListUiState
}

data class ExercicioFiltros(
    val busca: String = "",
    val grupoMuscular: GrupoMuscular? = null,
    val musculoIds: Set<String> = emptySet(),
    val aparelhoIds: Set<String> = emptySet(),
    val escopo: EscopoExercicio = EscopoExercicio.TODOS
)

class ExercicioViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<ExercicioListUiState>(ExercicioListUiState.Idle)
    val uiState: StateFlow<ExercicioListUiState> = _uiState.asStateFlow()

    private val _filtros = MutableStateFlow(ExercicioFiltros())
    val filtros: StateFlow<ExercicioFiltros> = _filtros.asStateFlow()

    fun atualizarFiltros(novo: ExercicioFiltros) {
        _filtros.value = novo
        carregar()
    }

    fun carregar(page: Int = 1) {
        val f = _filtros.value
        viewModelScope.launch {
            _uiState.value = ExercicioListUiState.Loading
            try {
                val resposta = RetrofitClient.exercicioApi.listar(
                    page = page,
                    limite = 20,
                    nome = f.busca.takeIf(String::isNotBlank),
                    grupoMuscular = f.grupoMuscular?.apiValue,
                    escopo = f.escopo.apiValue,
                    incluirMusculos = true,
                    incluirAparelhos = true
                )
                val pagina = resposta.data
                val brutos = pagina?.dados.orEmpty()
                val temFiltroClient = f.musculoIds.isNotEmpty() || f.aparelhoIds.isNotEmpty()

                var lista = brutos
                if (f.musculoIds.isNotEmpty()) {
                    lista = lista.filter { ex -> ex.musculos.any { it.musculoId in f.musculoIds } }
                }
                if (f.aparelhoIds.isNotEmpty()) {
                    lista = lista.filter { ex -> ex.aparelhos.any { it.aparelhoId in f.aparelhoIds } }
                }

                _uiState.value = if (lista.isEmpty()) {
                    ExercicioListUiState.Empty
                } else if (temFiltroClient) {
                    ExercicioListUiState.Success(
                        exercicios = lista,
                        total = lista.size,
                        page = 1,
                        totalPages = 1
                    )
                } else {
                    ExercicioListUiState.Success(
                        exercicios = lista,
                        total = pagina?.total ?: lista.size,
                        page = pagina?.page ?: 1,
                        totalPages = pagina?.totalPages ?: 1
                    )
                }
            } catch (e: HttpException) {
                val apiMsg = e.response()?.errorBody()?.string()?.let(::extractApiErrorMessage)
                _uiState.value = ExercicioListUiState.Error(apiMsg ?: mapHttpError(e.code()))
            } catch (e: Exception) {
                _uiState.value = ExercicioListUiState.Error(e.message ?: "Sem conexão com a internet")
            }
        }
    }

    private fun mapHttpError(code: Int): String = when (code) {
        401 -> "Sessão expirada. Faça login novamente"
        403 -> "Você não possui permissão para visualizar exercícios"
        422 -> "Parâmetros inválidos para consulta de exercícios"
        in 500..599 -> "Servidor indisponível no momento"
        else -> "Falha ao carregar exercícios. Código HTTP: $code"
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
