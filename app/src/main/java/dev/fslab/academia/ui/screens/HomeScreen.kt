package dev.fslab.academia.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
//import dev.fslab.academia.ui.components.AppNavigationBar
import dev.fslab.academia.ui.components.AppNavigationBar
import dev.fslab.academia.ui.theme.AcademiaTheme
import dev.fslab.academia.ui.theme.LocalAcademiaColors

/**
 * HomeScreen - Tela principal após o login (sem NavigationBar)
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onLogout: () -> Unit = {},
    onNavigate: (Int) -> Unit = {}
) {
    val colors = LocalAcademiaColors.current
    var selectedIndex by remember { mutableIntStateOf(0) } // Início = index 0

    Scaffold(
        contentWindowInsets = WindowInsets.systemBars,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Spotter",
                        style = MaterialTheme.typography.headlineMedium
                    )
                },
                actions = {
                    IconButton(onClick = onLogout) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Logout,
                            contentDescription = "Sair",
                            tint = colors.textOnPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colors.primaryDark,
                    titleContentColor = colors.textOnPrimary
                )
            )
        },
        bottomBar = {
            AppNavigationBar(
                selectedIndex = selectedIndex,
                onItemSelected = { index ->
                    if (index == 0) selectedIndex = index  // Já está em Home
                    else onNavigate(index)                 // Navega para outra tela
                }
            )
        }
    ) { innerPadding ->
        when (selectedIndex) {
            0 -> HomeInicioContent(
                modifier = Modifier.padding(innerPadding),
                onDashboard = { onNavigate(3) },
                onLogout = onLogout
            )
            // 3 → Dashboard navega via onDashboard(), tratado no onItemSelected
            else -> HomeEmptyContent(modifier = Modifier.padding(innerPadding))
        }
    }
}

/**
 * HomeInicioContent - Conteúdo da aba Início
 */
@Composable
private fun HomeInicioContent(
    modifier: Modifier = Modifier,
    onDashboard: () -> Unit,
    onLogout: () -> Unit
) {
    val colors = LocalAcademiaColors.current

    val gradientBrush = Brush.verticalGradient(
        colors = listOf(
            colors.primaryDark,
            colors.primary,
            colors.primary.copy(alpha = 0.7f)
        )
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(gradientBrush)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(colors.primary),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = "Home",
                    tint = colors.textOnPrimary,
                    modifier = Modifier.size(50.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Bem-vindo!",
                color = colors.textOnPrimary,
                style = MaterialTheme.typography.displaySmall
            )

            Text(
                text = "Você está logado no Fila Cidadã",
                color = colors.textOnPrimary.copy(alpha = 0.8f),
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                QuickActionCard(
                    modifier = Modifier.weight(1f),
                    icon = Icons.Filled.QrCode,
                    titulo = "Entrar na Fila",
                    descricao = "Escaneie o QR Code"
                )
                QuickActionCard(
                    modifier = Modifier.weight(1f),
                    icon = Icons.Filled.ConfirmationNumber,
                    titulo = "Minhas Senhas",
                    descricao = "2 ativas"
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = colors.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Dashboard do Cidadão",
                        style = MaterialTheme.typography.headlineLarge,
                        color = colors.textPrimary
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Acompanhe suas filas, senhas e notificações em tempo real.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = colors.textSecondary,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = onDashboard,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colors.primary
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Dashboard,
                            contentDescription = "Dashboard",
                            tint = colors.textOnPrimary
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        Text(
                            text = "Abrir Dashboard",
                            style = MaterialTheme.typography.titleLarge,
                            color = colors.textOnPrimary
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = colors.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                ) {
                    Button(
                        onClick = onLogout,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colors.error
                        )
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Logout,
                            contentDescription = "Sair",
                            tint = colors.textOnPrimary
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        Text(
                            text = "Sair",
                            style = MaterialTheme.typography.titleLarge,
                            color = colors.textOnPrimary
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

/**
 * HomeEmptyContent - Conteúdo vazio para abas sem implementação
 */
@Composable
private fun HomeEmptyContent(modifier: Modifier = Modifier) {
    val colors = LocalAcademiaColors.current

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        colors.primaryDark,
                        colors.primary,
                        colors.primary.copy(alpha = 0.7f)
                    )
                )
            )
    )
}

/**
 * QuickActionCard - Card de ação rápida na tela inicial
 */
@Composable
private fun QuickActionCard(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    titulo: String,
    descricao: String
) {
    val colors = LocalAcademiaColors.current

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = colors.surface.copy(alpha = 0.9f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = titulo,
                tint = colors.primary,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = titulo,
                style = MaterialTheme.typography.titleMedium,
                color = colors.textPrimary,
                textAlign = TextAlign.Center
            )
            Text(
                text = descricao,
                style = MaterialTheme.typography.labelMedium,
                color = colors.textSecondary,
                textAlign = TextAlign.Center
            )
        }
    }
}