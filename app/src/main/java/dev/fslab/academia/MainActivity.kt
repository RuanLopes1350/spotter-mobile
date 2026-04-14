package dev.fslab.academia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.fslab.academia.network.CookieManager
import dev.fslab.academia.ui.screens.HomeScreen
import dev.fslab.academia.ui.screens.auth.ExerciciosScreen
import dev.fslab.academia.ui.screens.auth.LoginScreen
import dev.fslab.academia.ui.theme.AcademiaTheme
import dev.fslab.academia.ui.viewmodel.AuthState
import dev.fslab.academia.ui.viewmodel.AuthViewModel
import dev.fslab.academia.ui.viewmodel.ExerciciosViewModel

class MainActivity : ComponentActivity() {

    private val authViewModel: AuthViewModel by viewModels()
    private val exerciciosViewModel: ExerciciosViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CookieManager.init(applicationContext)
        enableEdgeToEdge()
        setContent {
            AcademiaApp(
                authViewModel = authViewModel,
                exerciciosViewModel = exerciciosViewModel
            )
        }
    }
}

@Composable
fun AcademiaApp(
    authViewModel: AuthViewModel,
    exerciciosViewModel: ExerciciosViewModel
) {
    val systemDark = isSystemInDarkTheme()
    var isDarkTheme by remember { mutableStateOf(systemDark) }

    val authState by authViewModel.authState.collectAsState()
    val currentUser by authViewModel.currentUser.collectAsState()
    val exerciciosState by exerciciosViewModel.uiState.collectAsState()

    AcademiaTheme(darkTheme = isDarkTheme) {
        val navController = rememberNavController()

        LaunchedEffect(Unit) {
            authViewModel.checkSession()
        }

        LaunchedEffect(authState) {
            when (authState) {
                is AuthState.Success -> {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                        launchSingleTop = true
                    }
                }
                AuthState.Idle -> {
                    navController.navigate("login") {
                        popUpTo("login") { inclusive = true }
                        launchSingleTop = true
                    }
                }
                else -> Unit
            }
        }

        NavHost(
            navController = navController,
            startDestination = "login"
        ) {
            composable("login") {
                LoginScreen(
                    isDarkTheme = isDarkTheme,
                    isLoading = authState is AuthState.Loading,
                    errorMessage = (authState as? AuthState.Error)?.message,
                    onToggleTheme = { isDarkTheme = !isDarkTheme },
                    onEsqueciSenha = { /* TODO */ },
                    onRegister = { /* TODO */ },
                    onLogin = { email, password ->
                        authViewModel.loginUser(email = email, password = password)
                    }
                )
            }
            composable("home") {
                HomeScreen(
                    nome = currentUser?.name.orEmpty(),
                    isDarkTheme = isDarkTheme,
                    onToggleTheme = { isDarkTheme = !isDarkTheme },
                    onLogout = { authViewModel.logout() },
                    onOpenExercicios = {
                        navController.navigate("exercicios") {
                            launchSingleTop = true
                        }
                    }
                )
            }

            composable("exercicios") {
                LaunchedEffect(Unit) {
                    exerciciosViewModel.carregarExercicios()
                }

                ExerciciosScreen(
                    uiState = exerciciosState,
                    isDarkTheme = isDarkTheme,
                    onToggleTheme = { isDarkTheme = !isDarkTheme },
                    onBackHome = {
                        navController.navigate("home") {
                            launchSingleTop = true
                        }
                    },
                    onReload = {
                        exerciciosViewModel.carregarExercicios(force = true)
                    }
                )
            }
        }
    }
}