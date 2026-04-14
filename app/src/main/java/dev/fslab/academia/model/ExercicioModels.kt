package dev.fslab.academia.model

import com.google.gson.annotations.SerializedName

data class ExercicioMusculoData(
    @SerializedName("musculo_id") val musculoId: String,
    @SerializedName("tipo_ativacao") val tipoAtivacao: String,
    @SerializedName("nome") val nome: String,
    @SerializedName("grupo_muscular") val grupoMuscular: String
)

data class ExercicioData(
    @SerializedName("id") val id: String,
    @SerializedName("nome") val nome: String,
    @SerializedName("descricao") val descricao: String? = null,
    @SerializedName("aluno_id") val alunoId: String? = null,
    @SerializedName("deletado_em") val deletadoEm: String? = null,
    @SerializedName("musculos") val musculos: List<ExercicioMusculoData> = emptyList()
)

data class ExercicioPaginationData(
    @SerializedName("dados") val dados: List<ExercicioData> = emptyList(),
    @SerializedName("total") val total: Int = 0,
    @SerializedName("page") val page: Int = 1,
    @SerializedName("limite") val limite: Int = 0,
    @SerializedName("totalPages") val totalPages: Int = 0
)

data class ExercicioListResponse(
    @SerializedName("error") val error: Boolean = false,
    @SerializedName("code") val code: Int? = null,
    @SerializedName("message") val message: String? = null,
    @SerializedName("data") val data: ExercicioPaginationData? = null,
    @SerializedName("errors") val errors: List<Map<String, Any?>> = emptyList()
)
