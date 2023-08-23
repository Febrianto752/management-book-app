package com.example.managebookapp

sealed class Routes(val route: String) {
    object Login : Routes("Login")
    object Home : Routes("Home")
    object BookDetail : Routes("BookDetail/{bookIndex}")
}