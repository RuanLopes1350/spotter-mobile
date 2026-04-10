package dev.fslab.academia.network

import dev.fslab.academia.model.GetSessionResponse
import dev.fslab.academia.model.ExercicioListResponse
import dev.fslab.academia.model.LoginRequest
import dev.fslab.academia.model.LoginResponse
import dev.fslab.academia.model.RegisterRequest
import dev.fslab.academia.model.RegisterResponse
import dev.fslab.academia.model.UserData
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApi {
    @POST("auth/sign-in/email")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST("auth/sign-up/email")
    suspend fun register(@Body request: RegisterRequest): RegisterResponse

    @GET("auth/get-session")
    suspend fun getSession(): GetSessionResponse

    @GET("me")
    suspend fun getProfile(): UserData

    @GET("exercicios")
    suspend fun getExercicios(
        @Query("page") page: Int = 1,
        @Query("limite") limite: Int = 20,
        @Query("escopo") escopo: String = "TODOS",
        @Query("incluir_musculos") incluirMusculos: Boolean = true
    ): ExercicioListResponse

    @POST("auth/sign-out")
    suspend fun logout()
}