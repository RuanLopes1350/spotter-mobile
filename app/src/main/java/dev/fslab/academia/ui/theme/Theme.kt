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
    // Cores para cards de features (variam entre light/dark)
    val featureBlue: Color,
    val featureGreen: Color,
    val featureOrange: Color,
    val featureCyan: Color,
    val featurePink: Color,
    val featureRed: Color,
    val isDark: Boolean = false  // Flag para identificar o tema
)

/**
 * Cores para tema claro — Verde/Branco com accent #5DD62C
 */
val LightAcademiaColors = AcademiaColors(
    background = PrimaryLightBlue,
    backgroundGradientStart = PrimaryLightBlue,
    backgroundGradientEnd = SurfaceLight,
    surface = SurfaceWhite,
    textPrimary = TextPrimary,
    textSecondary = TextSecondary,
    textTertiary = TextTertiary,
    textOnPrimary = DarkTextOnGreen,    // texto preto sobre botão verde
    textInput = TextBlack,
    primary = PrimaryBlue,
    primaryDark = PrimaryBlue,
    iconGray = IconGray,
    inputBorder = InputBorderGray,
    mediumGray = MediumGray,
    errorBackground = ErrorBackground,
    errorText = ErrorText,
    errorButton = ErrorButton,
    error = Color(0xFFDC2626),
    successBackground = SuccessBackground,
    successText = SuccessText,
    success = Color(0xFF10B981),
    lightGray = LightGray,
    // Cores para cards de features
    featureBlue = Color(0xFF4A6CF7),
    featureGreen = Color(0xFF16A34A),
    featureOrange = Color(0xFFF59E0B),
    featureCyan = Color(0xFF06B6D4),
    featurePink = Color(0xFFEC4899),
    featureRed = Color(0xFFEF4444),
    isDark = false
)

/**
 * Cores para tema escuro — Fábrica 4 (preto-esverdeado + accent #5DD62C)
 */
val DarkAcademiaTesteColors = AcademiaColors(
    background = DarkBg,
    backgroundGradientStart = DarkBg,
    backgroundGradientEnd = DarkBgGradient,
    surface = DarkSurface,
    textPrimary = DarkTextPrimary,
    textSecondary = DarkTextSecondary,
    textTertiary = DarkTextTertiary,
    textOnPrimary = DarkTextOnGreen,    // texto preto sobre botão verde
    textInput = DarkTextInput,
    primary = PrimaryBlue,              // #5DD62C — verde vibrante
    primaryDark = PrimaryBlue,          // mesmo verde (sem distinção no dark)
    iconGray = DarkIconColor,
    inputBorder = DarkInputBorder,
    mediumGray = DarkTextTertiary,      // placeholder dark
    errorBackground = Color(0xFF2E1212),
    errorText = Color(0xFFFF6B6B),
    errorButton = Color(0xFFEF4444),
    error = Color(0xFFFF6B6B),
    successBackground = Color(0xFF0F2A0F),
    successText = Color(0xFF6EED6E),
    success = PrimaryBlue,
    lightGray = DarkInputBg,            // fundo dos inputs dark
    // Cores para cards de features (Dark — vibrantes sobre fundo escuro)
    featureBlue = Color(0xFF6B8AFF),
    featureGreen = Color(0xFF4ADE80),
    featureOrange = Color(0xFFFFBB5C),
    featureCyan = Color(0xFF22D3EE),
    featurePink = Color(0xFFF472B6),
    featureRed = Color(0xFFFF6B81),
    isDark = true
)

/**
 * CompositionLocal para acessar as cores customizadas
 * compositionLocalOf permite mudanças dinâmicas e propaga recomposição
 * ao invés de usar um objeto singleton, usamos CompositionLocal
 * para que as cores possam ser reativas e mudem conforme o tema sem precisar reiniciar a aplicação
 *
 * ⚠️ Para o toggle de tema funcionar, o estado DEVE ser gerenciado no Activity/NavHost:
 *
 *   var isDark by remember { mutableStateOf(true) }
 *   AcademiaTheme(darkTheme = isDark) {
 *       LoginScreen(isDarkTheme = isDark, onToggleTheme = { isDark = !isDark })
 *   }
 */
val LocalAcademiaColors = compositionLocalOf { DarkAcademiaTesteColors }

/**
 * DarkColorScheme - Paleta Material 3 para tema escuro (Verde/Preto)
 */
private val DarkColorScheme = darkColorScheme(
    primary = PrimaryBlue,
    secondary = DarkTextSecondary,
    tertiary = PrimaryBlueDark,
    background = DarkBg,
    surface = DarkSurface,
    onPrimary = DarkTextOnGreen,
    onSecondary = DarkTextPrimary,
    onTertiary = DarkTextPrimary,
    onBackground = DarkTextPrimary,
    onSurface = DarkTextPrimary
)

/**
 * LightColorScheme - Paleta Material 3 para tema claro (Verde/Branco)
 */
private val LightColorScheme = lightColorScheme(
    primary = PrimaryBlue,
    secondary = SecondaryGray,
    tertiary = PrimaryBlueDark,
    background = PrimaryLightBlue,
    surface = SurfaceWhite,
    onPrimary = DarkTextOnGreen,
    onSecondary = TextPrimary,
    onTertiary = TextPrimary,
    onBackground = TextPrimary,
    onSurface = TextPrimary
)

/**
 * FilaTesteTheme - Função composable que aplica o tema à aplicação
 */
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

    // Seleciona as cores customizadas baseado no tema
    val AcademiaColors = if (darkTheme) DarkAcademiaTesteColors else LightAcademiaColors

    CompositionLocalProvider(LocalAcademiaColors provides AcademiaColors) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}