package com.beranju.mandirinewsapp

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