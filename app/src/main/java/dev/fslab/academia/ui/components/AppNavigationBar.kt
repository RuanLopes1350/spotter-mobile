package dev.fslab.academia.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import dev.fslab.academia.ui.theme.LocalAcademiaColors

/**
 * Modelo de dados para os itens da NavigationBar
 */
data class NavItemData(
    val label: String,
    val icon: ImageVector,
    val badgeCount: Int = 0
)

/**
 * Lista padrão de itens da NavigationBar usada em todas as telas
 */
val defaultNavItems = listOf(
    NavItemData("Início", Icons.Filled.Home),
    NavItemData("Filas", Icons.Filled.Search),
    NavItemData("Senhas", Icons.Filled.ConfirmationNumber, badgeCount = 2),
    NavItemData("Dashboard", Icons.Filled.Dashboard),
    NavItemData("Perfil", Icons.Filled.Person)
)

/**
 * AppNavigationBar - NavigationBar reutilizável do Material Design 3
 *
 * Componente compartilhado usado em todas as telas após o login.
 *
 * @param selectedIndex índice da aba selecionada
 * @param onItemSelected callback quando uma aba é clicada
 */
@Composable
fun AppNavigationBar(
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    val colors = LocalAcademiaColors.current

    NavigationBar(
        containerColor = colors.surface,
        contentColor = colors.primary
    ) {
        defaultNavItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedIndex == index,
                onClick = { onItemSelected(index) },
                icon = {
                    if (item.badgeCount > 0) {
                        BadgedBox(
                            badge = {
                                Badge(containerColor = colors.error) {
                                    Text(
                                        text = item.badgeCount.toString(),
                                        color = colors.textOnPrimary
                                    )
                                }
                            }
                        ) {
                            Icon(item.icon, contentDescription = item.label)
                        }
                    } else {
                        Icon(item.icon, contentDescription = item.label)
                    }
                },
                label = {
                    Text(
                        text = item.label,
                        style = MaterialTheme.typography.labelSmall
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = colors.primary,
                    selectedTextColor = colors.primary,
                    unselectedIconColor = colors.mediumGray,
                    unselectedTextColor = colors.mediumGray,
                    indicatorColor = colors.primary.copy(alpha = 0.15f)
                )
            )
        }
    }
}

