package dev.fslab.academia.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.fslab.academia.model.TreinadorData
import dev.fslab.academia.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface BuscarTreinadorUiState {
    data object Idle : BuscarTreinadorUiState
    data object Loading : BuscarTreinadorUiState
    data class Success(val treinadores: List<TreinadorData>) : BuscarTreinadorUiState
    data class Error(val message: String) : BuscarTreinadorUiState
}

class BuscarTreinadorViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<BuscarTreinadorUiState>(BuscarTreinadorUiState.Idle)
    val uiState: StateFlow<BuscarTreinadorUiState> = _uiState.asStateFlow()

    private val _search = MutableStateFlow("")
    val search: StateFlow<String> = _search.asStateFlow()

    private val _filtrarMinhaAcademia = MutableStateFlow(false)
    val filtrarMinhaAcademia: StateFlow<Boolean> = _filtrarMinhaAcademia.asStateFlow()

    private var todosOsTreinadores: List<TreinadorData> = emptyList()
    private var alunoAcademiaId: String? = null

    fun carregar(academiaId: String? = null) {
        alunoAcademiaId = academiaId
        buscarDaApi()
    }

    fun toggleFiltroAcademia() {
        if (alunoAcademiaId == null) return
        _filtrarMinhaAcademia.value = !_filtrarMinhaAcademia.value
        if (_uiState.value is BuscarTreinadorUiState.Success) {
            _uiState.value = BuscarTreinadorUiState.Success(filtrar(_search.value))
        }
    }

    private fun buscarDaApi() {
        if (_uiState.value is BuscarTreinadorUiState.Loading) return
        viewModelScope.launch {
            _uiState.value = BuscarTreinadorUiState.Loading
            try {
                val response = RetrofitClient.treinadorApi.getAllTreinadores(limite = 100)
                todosOsTreinadores = response.data?.dados.orEmpty()
                _uiState.value = BuscarTreinadorUiState.Success(filtrar(_search.value))
            } catch (e: Exception) {
                _uiState.value = BuscarTreinadorUiState.Error(e.message ?: "Erro ao carregar treinadores")
            }
        }
    }

    fun onSearchChange(query: String) {
        _search.value = query
        if (_uiState.value is BuscarTreinadorUiState.Success) {
            _uiState.value = BuscarTreinadorUiState.Success(filtrar(query))
        }
    }

    private fun filtrar(query: String): List<TreinadorData> {
        var lista = todosOsTreinadores
        if (_filtrarMinhaAcademia.value && alunoAcademiaId != null) {
            lista = lista.filter { it.academiaId == alunoAcademiaId }
        }
        if (query.isBlank()) return lista
        val q = query.trim().lowercase()
        return lista.filter { t ->
            t.nome.lowercase().contains(q) ||
            t.especializacao.lowercase().contains(q) ||
            (t.apresentacao?.lowercase()?.contains(q) == true)
        }
    }
}
