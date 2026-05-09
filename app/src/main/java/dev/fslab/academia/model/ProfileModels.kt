package dev.fslab.academia.model

import com.google.gson.annotations.SerializedName

/**
 * Representa o gênero do usuário conforme o ENUM do banco.
 */
enum class Genero(val valor: String) {
    @SerializedName("M") MASCULINO("M"),
    @SerializedName("F") FEMININO("F"),
    @SerializedName("P") PREFIRO_NAO_DIZER("P")
}

/**
 * Modelo completo para o perfil do Aluno, incluindo dados físicos (Figma Node 157:846).
 */
data class AlunoProfileData(
    @SerializedName("id") val id: String,
    @SerializedName("nome") val nome: String,
    @SerializedName("username") val username: String? = null,
    @SerializedName("email") val email: String,
    @SerializedName("data_nascimento") val dataNascimento: String,
    @SerializedName("telefone") val telefone: String? = null,
    @SerializedName("sexo") val sexo: Genero,
    @SerializedName("url_foto") val urlFoto: String? = null,
    @SerializedName("peso_atual") val pesoKg: Double? = null,
    @SerializedName("altura") val alturaCm: Int? = null,
    @SerializedName("academia_id") val academiaId: String
)

/**
 * Modelo completo para o perfil do Treinador.
 */
data class TreinadorProfileData(
    @SerializedName("id") val id: String,
    @SerializedName("nome") val nome: String,
    @SerializedName("username") val username: String? = null,
    @SerializedName("email") val email: String,
    @SerializedName("data_nascimento") val dataNascimento: String,
    @SerializedName("sexo") val sexo: Genero,
    @SerializedName("url_foto") val urlFoto: String? = null,
    @SerializedName("cref") val cref: String,
    @SerializedName("especializacao") val especializacao: String,
    @SerializedName("graduacao") val graduacao: String,
    @SerializedName("turnos") val turnos: List<String> = emptyList()
)

/**
 * Respostas da API para perfis.
 */
data class AlunoProfileResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("data") val data: AlunoProfileData
)

data class TreinadorProfileResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("data") val data: TreinadorProfileData
)
