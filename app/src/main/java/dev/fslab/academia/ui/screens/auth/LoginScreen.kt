package dev.fslab.academia.ui.screens.auth

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.fslab.academia.ui.theme.AcademiaTheme
import dev.fslab.academia.ui.theme.LocalAcademiaColors

/**
 * LoginScreen - Tela de autenticação da aplicação Fábrica 4
 *
 * ⚠️ Para o toggle de tema funcionar, gerencie o estado no Activity/NavHost:
 *
 *   var isDark by remember { mutableStateOf(true) }
 *   AcademiaTheme(darkTheme = isDark) {
 *       LoginScreen(
 *           isDarkTheme = isDark,
 *           onToggleTheme = { isDark = !isDark }
 *       )
 *   }
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    isDarkTheme: Boolean = true,
    onToggleTheme: () -> Unit = {},
    onEsqueciSenha: (String) -> Unit = {},
    onRegister: () -> Unit = {},
    onLogin: () -> Unit = {}
) {
    val colors = LocalAcademiaColors.current

    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var senhaVisivel by remember { mutableStateOf(false) }

    // Fade-in ao entrar na tela
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { visible = true }
    val fadeAlpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(700),
        label = "fadeIn"
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        colors.backgroundGradientStart,
                        colors.backgroundGradientEnd
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .imePadding()
                .padding(horizontal = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // ─── Botão alternar tema ─────────────────────────────────
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 52.dp),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = onToggleTheme) {
                    Icon(
                        imageVector = if (isDarkTheme) Icons.Filled.LightMode else Icons.Filled.DarkMode,
                        contentDescription = if (isDarkTheme) "Modo claro" else "Modo escuro",
                        tint = colors.textSecondary,
                        modifier = Modifier.size(22.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // ─── Ícone do app ─────────────────────────────────────────
            Box(
                modifier = Modifier
                    .alpha(fadeAlpha)
                    .size(80.dp)
                    .shadow(
                        elevation = 20.dp,
                        shape = RoundedCornerShape(20.dp),
                        ambientColor = colors.primary.copy(alpha = 0.4f),
                        spotColor = colors.primary.copy(alpha = 0.5f)
                    )
                    .clip(RoundedCornerShape(20.dp))
                    .background(colors.lightGray),   // lightGray = DarkInputBg no dark, LightGray no light
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.FitnessCenter,
                    contentDescription = "Logo Fábrica 4",
                    tint = colors.primary,
                    modifier = Modifier.size(40.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // ─── "FÁBRICA 4" ──────────────────────────────────────────
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            color = colors.textPrimary,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 30.sp,
                            letterSpacing = 3.sp
                        )
                    ) { append("Spot") }
                    withStyle(
                        SpanStyle(
                            color = colors.primary,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 30.sp,
                            letterSpacing = 3.sp
                        )
                    ) { append("ter") }
                },
                modifier = Modifier.alpha(fadeAlpha)
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "PROFESSIONAL TRAINING CENTER",
                color = colors.textSecondary,
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 3.sp,
                modifier = Modifier.alpha(fadeAlpha)
            )

            Spacer(modifier = Modifier.height(52.dp))

            // ─── Formulário ───────────────────────────────────────────
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .alpha(fadeAlpha)
            ) {

                Text(
                    text = "Bem-vindo de volta",
                    color = colors.textPrimary,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally) // Diz à Column para centralizar este componente
                        .padding(bottom = 28.dp)
                )

                // ── E-MAIL ──────────────────────────────────────────
                Text(
                    text = "E-MAIL",
                    color = colors.textSecondary,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 2.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = {
                        Text("seu@email.com", color = colors.mediumGray)
                    },
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Email,
                            contentDescription = "Email",
                            tint = colors.iconGray,
                            modifier = Modifier.size(20.dp)
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = colors.inputBorder,
                        focusedBorderColor = colors.primary.copy(alpha = 0.6f),
                        unfocusedContainerColor = colors.lightGray,
                        focusedContainerColor = colors.lightGray,
                        cursorColor = colors.primary,
                        focusedTextColor = colors.textInput,
                        unfocusedTextColor = colors.textInput
                    )
                )

                Spacer(modifier = Modifier.height(20.dp))

                // ── SENHA ───────────────────────────────────────────
                Text(
                    text = "SENHA",
                    color = colors.textSecondary,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 2.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                OutlinedTextField(
                    value = senha,
                    onValueChange = { senha = it },
                    placeholder = {
                        Text("••••••••", color = colors.mediumGray)
                    },
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Lock,
                            contentDescription = "Senha",
                            tint = colors.iconGray,
                            modifier = Modifier.size(20.dp)
                        )
                    },
                    trailingIcon = {
                        IconButton(onClick = { senhaVisivel = !senhaVisivel }) {
                            Icon(
                                imageVector = if (senhaVisivel) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                contentDescription = if (senhaVisivel) "Ocultar senha" else "Mostrar senha",
                                tint = colors.iconGray,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    visualTransformation = if (senhaVisivel) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = colors.inputBorder,
                        focusedBorderColor = colors.primary.copy(alpha = 0.6f),
                        unfocusedContainerColor = colors.lightGray,
                        focusedContainerColor = colors.lightGray,
                        cursorColor = colors.primary,
                        focusedTextColor = colors.textInput,
                        unfocusedTextColor = colors.textInput
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                // ── Esqueci minha senha ─────────────────────────────
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = "Esqueci minha senha",
                        style = MaterialTheme.typography.labelMedium,
                        color = colors.textSecondary,
                        modifier = Modifier.clickable { onEsqueciSenha(email) }
                    )
                }

                Spacer(modifier = Modifier.height(36.dp))

                // ── Botão ENTRAR ────────────────────────────────────
                Button(
                    onClick = { onLogin() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colors.primary
                    )
                ) {
                    Text(
                        text = "ENTRAR  →",
                        color = colors.textOnPrimary,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = 2.sp
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.height(40.dp))

            // ─── Rodapé ───────────────────────────────────────────────
            Row(
                modifier = Modifier
                    .padding(bottom = 48.dp)
                    .alpha(fadeAlpha),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Ainda não é membro?  ",
                    style = MaterialTheme.typography.bodyMedium,
                    color = colors.textSecondary
                )
                Text(
                    text = "Comece agora",
                    style = MaterialTheme.typography.labelLarge,
                    color = colors.primary,
                    modifier = Modifier.clickable { onRegister() }
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, name = "Dark Theme")
@Composable
fun LoginScreenPreview() {
    AcademiaTheme(darkTheme = true) {
        LoginScreen(isDarkTheme = true)
    }
}

@Preview(showBackground = true, showSystemUi = true, name = "Light Theme")
@Composable
fun LoginScreenLightPreview() {
    AcademiaTheme(darkTheme = false) {
        LoginScreen(isDarkTheme = false)
    }
}