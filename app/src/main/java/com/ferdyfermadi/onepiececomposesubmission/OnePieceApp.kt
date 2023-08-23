package com.ferdyfermadi.onepiececomposesubmission

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ferdyfermadi.onepiececomposesubmission.ui.navigation.NavigationItem
import com.ferdyfermadi.onepiececomposesubmission.ui.navigation.Screen
import com.ferdyfermadi.onepiececomposesubmission.ui.screen.detail.DetailScreen
import com.ferdyfermadi.onepiececomposesubmission.ui.screen.home.HomeScreen
import com.ferdyfermadi.onepiececomposesubmission.ui.screen.profile.ProfileScreen
import com.ferdyfermadi.onepiececomposesubmission.ui.theme.OnePieceComposeSubmissionTheme

@Composable
fun OnePieceApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        bottomBar = {
            BottomBar(navController)
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { id ->
                        navController.navigate(Screen.Detail.createRouteName(id))
                    }
                )
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }

            composable(Screen.Detail.route,
                arguments = listOf(
                    navArgument("id") { type = NavType.IntType })
            ) {
                val id = it.arguments?.getInt("id")
                DetailScreen(id!!)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun OnePieceAppPreview() {
    OnePieceComposeSubmissionTheme {
        OnePieceApp()
    }
}

@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    BottomNavigation(
        modifier = modifier
    ) {
        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(R.string.menu_profile),
                icon = Icons.Default.AccountCircle,
                screen = Screen.Profile
            ),
        )
        BottomNavigation {
            navigationItems.map { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title
                        )
                    },
                    label = { Text(item.title) },
                    selected = currentRoute == item.screen.route,
                    onClick = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}