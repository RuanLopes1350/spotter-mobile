package dev.fslab.academia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dev.fslab.academia.ui.screens.auth.LoginScreen
import dev.fslab.academia.ui.screens.HomeScreen
import dev.fslab.academia.ui.theme.AcademiaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AcademiaApp()
        }
    }
}

@Composable
fun AcademiaApp() {
    val systemDark = isSystemInDarkTheme()
    var isDarkTheme by remember { mutableStateOf(systemDark) }

    AcademiaTheme(darkTheme = isDarkTheme) {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = "login"
        ) {
            composable("login") {
                LoginScreen(
                    isDarkTheme = isDarkTheme,
                    onToggleTheme = { isDarkTheme = !isDarkTheme },
                    onEsqueciSenha = { email ->
                        navController.navigate("esqueci_senha?email=$email")
                    },
                    onRegister = { navController.navigate("cadastro") },
                    onLogin = { navController.navigate("home") }
                )
            }
            composable("home") {
                HomeScreen(
                    isDarkTheme = isDarkTheme,
                    onToggleTheme = { isDarkTheme = !isDarkTheme },
                    onLogout = { navController.navigate("login") }
                )
            }
        }
    }
}