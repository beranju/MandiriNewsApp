package com.beranju.mandirinewsapp.ui.navigation

/**
 * list of screen
 * route akan digunakan pada composable sebagai route dari screen
 */
sealed class Screens(val route: String) {
    object Home: Screens("home")
    object Favorite: Screens("favorite")
    object Detail: Screens("detail")
}