package dev.fslab.academia.model

import com.google.gson.annotations.SerializedName

// ####################################################################################
//                       MODELOS DE REQUISIÇÕES
// ####################################################################################

// Login
data class LoginRequest(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("rememberMe") val rememberMe: Boolean,
    @SerializedName("callBackUrl") val callbackUrl: String
)

// Registro
data class RegisterRequest(
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("confirmPassword") val confirmPassword: String,
    @SerializedName("image") val image: String,
    @SerializedName("callBackUrl") val callbackUrl: String
)

// ####################################################################################
//                       MODELOS DE RESPOSTAS
// ####################################################################################

// Login
data class SessionData(
    @SerializedName("id") val id: String,
    @SerializedName("userId") val userId: String,
    @SerializedName("token") val token: String,
    @SerializedName("expiresAt") val expiresAt: String
)

data class UserData(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("image") val image: String
)

data class LoginResponse(
    @SerializedName("session") val session: SessionData,
    @SerializedName("user") val user: UserData
)

// Registro
data class RegisterResponse(
    @SerializedName("session") val session: SessionData,
    @SerializedName("user") val user: UserData
)

// Get Session
data class GetSessionResponse(
    @SerializedName("session") val session: SessionData,
    @SerializedName("user") val user: UserData
)

 // Me - Eu
 data class MeResponse(
     @SerializedName("success") val success: Boolean,
     @SerializedName("data") val data: UserData
 )