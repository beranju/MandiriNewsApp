package com.beranju.mandirinewsapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.beranju.mandirinewsapp.ui.navigation.Screens
import com.beranju.mandirinewsapp.ui.screen.detail.DetailScreen
import com.beranju.mandirinewsapp.ui.screen.home.HomeScreen
import com.beranju.mandirinewsapp.ui.screen.save.FavoriteScreen
import com.beranju.mandirinewsapp.ui.theme.MandiriNewsAppTheme

@Composable
fun BaseScreen(
    modifier: Modifier = Modifier,
    navController:NavHostController = rememberNavController(),
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Mandiri News",
                        fontFamily = Fon
                    )
                }
            )
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
                HomeScreen()
            }
            composable(Screens.Favorite.route){
                FavoriteScreen()
            }
            composable(Screens.Detail.route){
                DetailScreen()
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