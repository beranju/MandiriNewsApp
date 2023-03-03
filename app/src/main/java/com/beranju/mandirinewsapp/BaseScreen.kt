package com.beranju.mandirinewsapp

import android.net.Uri
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.beranju.mandirinewsapp.domain.model.NewsModel
import com.beranju.mandirinewsapp.ui.navigation.Screens
import com.beranju.mandirinewsapp.ui.screen.detail.DetailScreen
import com.beranju.mandirinewsapp.ui.screen.home.HomeScreen
import com.beranju.mandirinewsapp.ui.screen.save.FavoriteScreen
import com.beranju.mandirinewsapp.ui.theme.MandiriNewsAppTheme
import com.beranju.mandirinewsapp.utils.NewsModelType
import com.google.gson.Gson

@Composable
fun BaseScreen(
    modifier: Modifier = Modifier,
    navController:NavHostController = rememberNavController(),
) {
    Scaffold(
        modifier = modifier,
    ) { innerPadding ->
        /**
         * referensi => https://developer.android.com/jetpack/compose/navigation?hl=id
         */
        NavHost(
            navController = navController,
            startDestination = Screens.Home.route,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(Screens.Home.route){
                HomeScreen(
                    onClickItem = {data ->
                        val dataJson = Uri.encode(Gson().toJson(data))
                        navController.navigate(Screens.Detail.createRoute(dataJson))
                    }
                )
            }
            composable(Screens.Favorite.route){
                FavoriteScreen()
            }
            composable(
                route = Screens.Detail.route,
                arguments = listOf(
                    navArgument("data"){
                        // ** custom navtype for parcelable object
                        type = NewsModelType()
                    }
                )
            ){
                val data = it.arguments?.getParcelable<NewsModel>("data") ?: NewsModel(1)
                DetailScreen(
                    data = data,
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
        }

    }
    
}


@Preview(showBackground = true)
@Composable
fun BaseScreenPreview() {
    MandiriNewsAppTheme {
        BaseScreen()
    }
    
}