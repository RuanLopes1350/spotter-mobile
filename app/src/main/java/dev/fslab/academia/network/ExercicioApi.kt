package dev.fslab.academia.network

import dev.fslab.academia.model.ExercicioListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ExercicioApi {
    @GET("exercicios")
    suspend fun listar(
        @Query("page") page: Int = 1,
        @Query("limite") limite: Int = 20,
        @Query("nome") nome: String? = null,
        @Query("grupo_muscular") grupoMuscular: String? = null,
        @Query("tipo_ativacao") tipoAtivacao: String? = null,
        @Query("aluno_id") alunoId: String? = null,
        @Query("escopo") escopo: String? = null,
        @Query("em_uso") emUso: Boolean? = null,
        @Query("ordem_nome") ordemNome: String? = null,
        @Query("incluir_musculos") incluirMusculos: Boolean = true,
        @Query("incluir_aparelhos") incluirAparelhos: Boolean = true
    ): ExercicioListResponse
}
