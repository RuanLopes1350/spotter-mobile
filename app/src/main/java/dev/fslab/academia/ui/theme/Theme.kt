package dev.fslab.academia.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

/**
 * Cores customizadas para a aplicação que mudam conforme o tema
 */
data class AcademiaColors(
    val background: Color,
    val backgroundGradientStart: Color,
    val backgroundGradientEnd: Color,
    val surface: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val textTertiary: Color,
    val textOnPrimary: Color,
    val textInput: Color,
    val primary: Color,
    val primaryDark: Color,
    val iconGray: Color,
    val inputBorder: Color,
    val mediumGray: Color,
    val errorBackground: Color,
    val errorText: Color,
    val errorButton: Color,
    val error: Color,
    val successBackground: Color,
    val successText: Color,
    val success: Color,
    val lightGray: Color,
    val featureBlue: Color,
    val featureGreen: Color,
    val featureOrange: Color,
    val featureCyan: Color,
    val featurePink: Color,
    val featureRed: Color,
    val isDark: Boolean = false
)

/**
 * Cores para tema escuro — Fábrica 4 (Mapeado direto do Design System)
 */
val DarkAcademiaTesteColors = AcademiaColors(
    background = DarkBg,
    backgroundGradientStart = DarkBg,
    backgroundGradientEnd = DarkBgGradient,
    surface = SurfaceDark,                // #1E1E1E - Cor exata dos cards do print
    textPrimary = TextWhite,              // #F5F5F5 - Cor exata do texto
    textSecondary = TextGraySecondary,
    textTertiary = DarkTextTertiary,
    textOnPrimary = TextDarkOnPrimary,    // #000000 - Texto PRETO sobre o botão Verde Neon
    textInput = TextWhite,
    primary = PrimaryNeon,                // #5DD62C - O Verde Neon vibrante
    primaryDark = SecondaryDarkGreen,     // Verde escuro de suporte
    iconGray = IconMuted,
    inputBorder = DarkInputBorder,
    mediumGray = TextGraySecondary,
    errorBackground = ErrorBackground,
    errorText = ErrorText,
    errorButton = ErrorButton,
    error = ErrorText,
    successBackground = SuccessBackground,
    successText = SuccessText,
    success = PrimaryNeon,
    lightGray = DarkInputBg,              // fundo dos inputs no dark
    featureBlue = Color(0xFF6B8AFF),
    featureGreen = Color(0xFF4ADE80),
    featureOrange = Color(0xFFFFBB5C),
    featureCyan = Color(0xFF22D3EE),
    featurePink = Color(0xFFF472B6),
    featureRed = Color(0xFFFF6B81),
    isDark = true
)

/**
 * Cores para tema claro — Mantidas baseadas na sua estrutura original
 */
val LightAcademiaColors = AcademiaColors(
    background = PrimaryLightGreen,
//    backgroundGradientStart = PrimaryLightGreen,
    backgroundGradientStart = SecondaryDarkGreen,
//    backgroundGradientEnd = SecondaryDarkGreen,
    backgroundGradientEnd = PrimaryLightGreen,
    surface = SurfaceWhite,
    textPrimary = TextPrimaryLight,
    textSecondary = TextSecondaryLight,
    textTertiary = TextSecondaryLight,
    textOnPrimary = TextDarkOnPrimary,
    textInput = TextPrimaryLight,
    primary = PrimaryNeon,
    primaryDark = SecondaryDarkGreen,
    iconGray = Color(0xFF5A8A48),
    inputBorder = InputBorderLight,
    mediumGray = Color(0xFF9AB09A),
    errorBackground = Color(0xFFFFEBEE),
    errorText = Color(0xFFC62828),
    errorButton = Color(0xFFEF4444),
    error = Color(0xFFDC2626),
    successBackground = Color(0xFFE8F5E9),
    successText = Color(0xFF2E7D32),
    success = Color(0xFF10B981),
    lightGray = LightGray,
    featureBlue = Color(0xFF4A6CF7),
    featureGreen = Color(0xFF16A34A),
    featureOrange = Color(0xFFF59E0B),
    featureCyan = Color(0xFF06B6D4),
    featurePink = Color(0xFFEC4899),
    featureRed = Color(0xFFEF4444),
    isDark = false
)

val LocalAcademiaColors = compositionLocalOf { DarkAcademiaTesteColors }

/**
 * DarkColorScheme - Paleta Material 3
 */
private val DarkColorScheme = darkColorScheme(
    primary = PrimaryNeon,
    secondary = SecondaryDarkGreen,
    tertiary = PrimaryNeon,
    background = DarkBg,
    surface = SurfaceDark,
    onPrimary = TextDarkOnPrimary,
    onSecondary = TextWhite,
    onTertiary = TextWhite,
    onBackground = TextWhite,
    onSurface = TextWhite
)

/**
 * LightColorScheme - Paleta Material 3
 */
private val LightColorScheme = lightColorScheme(
    primary = PrimaryNeon,
    secondary = SecondaryDarkGreen,
    tertiary = PrimaryNeon,
    background = PrimaryLightGreen,
    surface = SurfaceWhite,
    onPrimary = TextDarkOnPrimary,
    onSecondary = TextPrimaryLight,
    onTertiary = TextPrimaryLight,
    onBackground = TextPrimaryLight,
    onSurface = TextPrimaryLight
)

@Composable
fun AcademiaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val academiaColors = if (darkTheme) DarkAcademiaTesteColors else LightAcademiaColors

    CompositionLocalProvider(LocalAcademiaColors provides academiaColors) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography, // Lembre-se de configurar as fontes aqui!
            content = content
        )
    }
}