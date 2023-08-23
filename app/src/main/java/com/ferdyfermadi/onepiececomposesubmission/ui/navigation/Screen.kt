package com.ferdyfermadi.onepiececomposesubmission.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Profile : Screen("profile")
    object Detail : Screen("home/{id}") {
        fun createRouteName(id: Int) = "home/$id"
    }
}