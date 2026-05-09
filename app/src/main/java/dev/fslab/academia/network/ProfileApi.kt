package dev.fslab.academia.network

import dev.fslab.academia.model.AlunoProfileResponse
import dev.fslab.academia.model.TreinadorProfileResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.Part
import retrofit2.http.Path

interface ProfileApi {

    // ── Aluno ─────────────────────────────────────────────────────────────────

    @GET("alunos/me")
    suspend fun getAlunoProfile(): AlunoProfileResponse

    @Multipart
    @PATCH("alunos/{id}")
    suspend fun updateAlunoProfile(
        @Path("id") id: String,
        @Part("data") data: RequestBody,
        @Part foto: MultipartBody.Part? = null
    ): AlunoProfileResponse

    // ── Treinador ─────────────────────────────────────────────────────────────

    @GET("treinadores/me")
    suspend fun getTreinadorProfile(): TreinadorProfileResponse

    @Multipart
    @PATCH("treinadores/{id}")
    suspend fun updateTreinadorProfile(
        @Path("id") id: String,
        @Part("data") data: RequestBody,
        @Part foto: MultipartBody.Part? = null
    ): TreinadorProfileResponse
}
