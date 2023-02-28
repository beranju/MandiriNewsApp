package com.beranju.mandirinewsapp.ui.navigation

import com.beranju.mandirinewsapp.domain.model.NewsModel

/**
 * list of screen
 * route akan digunakan pada composable sebagai route dari screen
 */
sealed class Screens(val route: String) {
    object Home: Screens("home")
    object Favorite: Screens("favorite")
    object Detail: Screens("home/{data}"){
        fun createRoute(data: String) = "home/$data"
    }
}