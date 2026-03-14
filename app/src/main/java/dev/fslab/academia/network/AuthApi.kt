package dev.fslab.academia.network

import dev.fslab.academia.model.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/sign-in/email")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST("auth/sign-up/email")
    suspend fun register(@Body request: RegisterRequest): RegisterResponse

    @GET("auth/get-session")
    suspend fun getSession(): GetSessionResponse

    @GET("/me")
    suspend fun getProfile(): UserData

    @PATCH("auth/sign-out")
    suspend fun logout()
}