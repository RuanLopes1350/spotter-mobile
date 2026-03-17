package dev.fslab.academia.ui.theme

import androidx.compose.ui.graphics.Color

/**
 * PALETA DE CORES DA APLICAÇÃO - FÁBRICA 4
 *
 * Baseado no Design System oficial.
 * As cores foram renomeadas para refletir com precisão seus tons reais,
 * evitando ambiguidades (ex: variáveis com nome 'Blue' guardando 'Verde').
 */

// ==========================================
// CORES PRINCIPAIS (DESIGN SYSTEM)
// ==========================================

val PrimaryNeon = Color(0xFF5DD62C)        // Verde Neon - Botões principais (Iniciar Treino), FAB, Destaques
val SecondaryDarkGreen = Color(0xFF2A5C16) // Verde Escuro - Apoio, gradientes e badges
val PrimaryLightGreen = Color(0xFFF2F9EE)  // Verde muito claro para fundo do tema light


// ==========================================
// SUPERFÍCIES E FUNDOS (DARK MODE FOCUSED)
// ==========================================

val DarkBg = Color(0xFF0A0A0A)             // Fundo principal do App (Preto profundo)
val SurfaceDark = Color(0xFF1E1E1E)        // Superfície de Cards (ex: Workout Card, Botões secundários)
val DarkInputBg = Color(0xFF141414)        // Fundo isolado para o Input Text
val DarkBgGradient = Color(0xFF0D140D)     // Fundo com leve toque esverdeado para degradês


// ==========================================
// TEXTOS E TIPOGRAFIA
// ==========================================

val TextWhite = Color(0xFFF5F5F5)          // Texto Principal no Dark (Off-white para não cansar a vista)
val TextDarkOnPrimary = Color(0xFF000000)  // Texto Preto (Usado SOBRE o botão Verde Neon)
val TextGraySecondary = Color(0xFFA0A0A0)  // Texto secundário (descrições, placeholders do input)
val DarkTextTertiary = Color(0xFF5A7A52)   // Textos terciários e detalhes sutis


// ==========================================
// BORDAS E ÍCONES
// ==========================================

val DarkInputBorder = Color(0xFF2C2C2C)   // Borda sutil dos inputs dark
val DarkIconColor = Color(0xFF5DD62C)      // Ícones de destaque (mesmo verde neon)
val IconMuted = Color(0xFF757575)          // Ícones inativos ou secundários


// ==========================================
// CORES DE ESTADO (FEEDBACK)
// ==========================================

val ErrorBackground = Color(0xFF2E1212)    // Fundo para erro no dark
val ErrorText = Color(0xFFFF6B6B)          // Texto de erro legível no escuro
val ErrorButton = Color(0xFFEF4444)        // Vermelho padrão de alerta
val SuccessBackground = Color(0xFF0F2A0F)  // Fundo de sucesso sutil
val SuccessText = Color(0xFF6EED6E)        // Texto de sucesso (verde claro)


// ==========================================
// DASHBOARD ADMIN & FEATURES CARDS
// ==========================================

val CardBlue = Color(0xFF7C6AF6)           // Instituições Ativas
val CardOrange = Color(0xFFD97706)         // Pendentes Aprovação
val CardGreen = Color(0xFF16A34A)          // Filas Ativas
val CardYellow = Color(0xFFFBBF24)         // Aguardando
val CardPurple = Color(0xFFA855F7)         // Senhas Emitidas


// ==========================================
// CORES TEMA CLARO (LEGADO / ALTERNATIVO)
// ==========================================

val SurfaceWhite = Color.White
val SurfaceLight = Color(0xFFE8F5DF)
val TextPrimaryLight = Color(0xFF0D1A09)
val TextSecondaryLight = Color(0xFF3D6030)
val InputBorderLight = Color(0xFFCCDEC4)
val LightGray = Color(0xFFEEF5EB)