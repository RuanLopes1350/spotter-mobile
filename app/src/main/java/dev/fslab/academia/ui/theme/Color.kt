package dev.fslab.academia.ui.theme

import androidx.compose.ui.graphics.Color

/**
 * PALETA DE CORES DA APLICAÇÃO
 *
 * As cores são organizadas em:
 * 1. Cores Primárias - Cores principais da marca (azul)
 * 2. Cores Secundárias - Cores de suporte (cinza)
 * 3. Cores de Texto - Para diferentes níveis de hierarquia
 * 4. Cores de Estado - Erro, sucesso, alerta
 * 5. Cores de Superfície - Fundos e cards
 * 6. Cores de Compatibilidade - Para Material Design 3
 *
 * Usando uma paleta de cores consistente, toda a aplicação
 * fica visualmente harmônica e profissional.
 */



// Cores Primárias - Verde (Cor principal da marca)

val PrimaryBlue = Color(0xFF5DD62C)        // Verde vibrante - botões principais
val PrimaryBlueDark = Color(0xFF3EA81A)    // Verde escuro - estado pressionado
val PrimaryLightBlue = Color(0xFFF2F9EE)   // Verde muito claro - fundo do tema light



// Cores de Texto

val TextPrimary = Color(0xFF0D1A09)        // Texto principal - títulos e labels (light)
val TextSecondary = Color(0xFF3D6030)      // Texto secundário - descrições (light)
val TextTertiary = Color(0xFF7A9A70)       // Texto terciário - links e detalhes (light)
val TextOnPrimary = Color.White            // Texto sobre fundo primário verde
val TextBlack = Color(0xFF0D1A09)          // Texto escuro - campos de input (light)



// Cores Secundárias - Cinzas (Textos e bordas)

val SecondaryGray = Color(0xFF4A6B4A)      // Verde escuro para textos secundários (light)
val LightGray = Color(0xFFEEF5EB)          // Verde muito claro - fundo de inputs (light)
val MediumGray = Color(0xFF9AB09A)         // Cinza-verde - placeholder/desabilitado (light)
val DarkGray = Color(0xFF4A6B4A)           // Verde escuro - descrições
val BorderGray = Color(0xFFCCDEC4)         // Verde claro para bordas (light)
val InputBorderGray = Color(0xFFCCDEC4)    // Borda de inputs (light)
val IconGray = Color(0xFF5A8A48)           // Verde para ícones (light)



// Cores de Superfície

val SurfaceWhite = Color.White             // Fundo de cards (light)
val SurfaceLight = Color(0xFFE8F5DF)       // Fundo gradiente secundário (verde claro)
val BackgroundGradientStart = PrimaryLightBlue  // Início do gradiente de fundo (light)
val BackgroundGradientEnd = Color(0xFFE8F5DF)   // Fim do gradiente de fundo (light)



// Cores de Estado - Erro

val ErrorBackground = Color(0xFFFFEBEE)    // Fundo vermelho claro para erros
val ErrorText = Color(0xFFC62828)          // Texto vermelho para erros
val ErrorButton = Color(0xFFEF4444)        // Botão vermelho (logout, cancelar)



// Cores de Estado - Sucesso

val SuccessBackground = Color(0xFFE8F5E9)  // Fundo verde claro para sucesso
val SuccessText = Color(0xFF2E7D32)        // Texto verde para sucesso



// Cores para Dashboard Admin Geral

val CardBlue = Color(0xFF7C6AF6)           // Card roxo - Instituições Ativas
val CardOrange = Color(0xFFD97706)         // Card laranja/amarelo - Pendentes Aprovação
val CardGreen = Color(0xFF16A34A)          // Card verde - Filas Ativas
val CardYellow = Color(0xFFFBBF24)         // Card amarelo - Aguardando
val CardPurple = Color(0xFFA855F7)         // Card roxo - Senhas Emitidas
val WarningOrange = Color(0xFFFBBF24)      // Ícone de alerta laranja
val StatusOperational = Color(0xFF22C55E)  // Status operacional verde



// Cores específicas do Tema Escuro (Figma) — usadas em DarkAcademiaTesteColors

val DarkBg                = Color(0xFF0A0F0A)   // Fundo principal dark
val DarkBgGradient        = Color(0xFF0D140D)   // Fim do gradiente dark
val DarkSurface           = Color(0xFF161E16)   // Cards/superfícies dark
val DarkInputBg           = Color(0xFF111811)   // Fundo dos inputs dark
val DarkInputBorder       = Color(0xFF1E2E1E)   // Borda sutil dos inputs dark
val DarkTextPrimary       = Color(0xFFFFFFFF)   // Texto principal dark (branco)
val DarkTextSecondary     = Color(0xFF5A7A52)   // Texto secundário dark (verde acinzentado)
val DarkTextTertiary      = Color(0xFF354E2E)   // Placeholder/desabilitado dark
val DarkTextOnGreen       = Color(0xFF000000)   // Texto sobre botão verde (dark e light)
val DarkTextInput         = Color(0xFFE8F0E5)   // Texto digitado nos inputs dark
val DarkIconColor         = Color(0xFF3D6030)   // Ícones dentro dos inputs dark
val GreenMuted            = Color(0xFF1A3A0D)   // Verde escuro p/ badge/ícone do app (dark)



// Cores de Compatibilidade com Material Design 3

val Purple80 = Color(0xFFD0BCFF)           // Para tema escuro
val PurpleGrey80 = Color(0xFFCCC2DC)       // Para tema escuro
val Pink80 = Color(0xFFEFB8C8)             // Para tema escuro

val Purple40 = Color(0xFF6650A4)           // Para tema claro
val PurpleGrey40 = Color(0xFF625B71)       // Para tema claro
val Pink40 = Color(0xFF7D5260)             // Para tema claro