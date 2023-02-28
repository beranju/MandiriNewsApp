package com.beranju.mandirinewsapp

import android.net.Uri
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavArgument
import androidx.navigation.NavHostController
import androidx.navigation.NavType
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
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

@Composable
fun BaseScreen(
    modifier: Modifier = Modifier,
    navController:NavHostController = rememberNavController(),
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopBar()
        },
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
                    onClickItem = {
                        val dataJson = Uri.encode(Gson().toJson(it))
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
                val data = it.arguments?.getParcelable<NewsModel>("data")
                DetailScreen(data = data!!)
            }
        }

    }
    
}

@Composable
fun TopBar(
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(
                text = "Mandiri News",
                style = MaterialTheme.typography.h1
            )
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Default.Favorite, contentDescription = "Favorite")
            }
        },
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun BaseScreenPreview() {
    MandiriNewsAppTheme {
        BaseScreen()
    }
    
}