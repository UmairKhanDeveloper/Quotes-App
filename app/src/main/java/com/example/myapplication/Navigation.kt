package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.HomeScreen.route) {
        composable(Screens.HomeScreen.route) {
            HomeSceen(navController)
        }
        composable(Screens.FavoriteScreen.route) {
            FavoriteScreen(navController)
        }
        composable(Screens.SettingScreen.route) {
            SettingScreen(navController)
        }
        composable(Screens.FeaturePreviewScreen.route) {
            FeaturePreviewScreen(navController)
        }
        composable(Screens.QuotesCommunityScreen.route) {
            QuotesCommunityScreen(navController)
        }
        composable(Screens.AboutScreen.route) {
            AboutScreen(navController)
        }
        composable(Screens.PrivacyPolicyScreen.route) {
            PrivacyPolicyScreen(navController)
        }


    }
}

sealed class Screens(
    val route: String,
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {
    object HomeScreen : Screens(
        "HomeScreen",
        "Home",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home
    )

    object FavoriteScreen : Screens(
        "FavoriteScreen",
        "Favorites",
        selectedIcon = Icons.Filled.Favorite,
        unselectedIcon = Icons.Outlined.FavoriteBorder
    )

    object FeaturePreviewScreen : Screens(
        "FeaturePreviewScreen",
        "FeaturePreviewScreen",
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings
    )
    object QuotesCommunityScreen : Screens(
        "QuotesCommunityScreen",
        "QuotesCommunityScreen",
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings
    )
    object AboutScreen : Screens(
        "AboutScreen",
        "AboutScreen",
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings
    )
    object SettingScreen : Screens(
        "SettingScreen",
        "Settings",
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings
    )
    object PrivacyPolicyScreen : Screens(
        "PrivacyPolicyScreen",
        "PrivacyPolicyScreen",
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings
    )


}

@Composable
fun BottomNavigation(navController: NavController) {
    val items = listOf(
        Screens.HomeScreen,
        Screens.FavoriteScreen,
        Screens.SettingScreen
    )
    NavigationBar(containerColor = Color(0xFFE3F2FD )) {
        val navStack by navController.currentBackStackEntryAsState()
        val currentRoute = navStack?.destination?.route

        items.forEach { screen ->
            NavigationBarItem(
                selected = currentRoute == screen.route,
                onClick = {
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = {
                    Image(
                        imageVector = if (currentRoute == screen.route) screen.selectedIcon else screen.unselectedIcon,
                        contentDescription = screen.title,
                        colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(
                            if (currentRoute == screen.route) Color.Black else Color.Black
                        )
                    )
                }
                ,
                label = { Text(text = screen.title) },colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF1E88E5),
                    unselectedIconColor = Color.Black,
                    selectedTextColor = Color.Black,
                    unselectedTextColor = Color.Black,
                    indicatorColor = Color(0xFF90A4AE)
                )
            )
        }
    }
}

@Composable
fun NavEntry() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigation(navController) }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Navigation(navController)
        }
    }
}


