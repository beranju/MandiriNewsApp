package com.beranju.mandirinewsapp

import android.net.Uri
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
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
import com.beranju.mandirinewsapp.ui.screen.detail.DetailViewModel
import com.beranju.mandirinewsapp.ui.screen.home.HomeScreen
import com.beranju.mandirinewsapp.ui.screen.save.FavoriteScreen
import com.beranju.mandirinewsapp.ui.screen.search.SearchScreen
import com.beranju.mandirinewsapp.ui.theme.MandiriNewsAppTheme
import com.beranju.mandirinewsapp.utils.NewsModelType
import com.google.gson.Gson
import org.koin.androidx.compose.koinViewModel

@Composable
fun BaseScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
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
        ) {
            composable(Screens.Home.route) {
                HomeScreen(
                    onClickItem = { data ->
                        val dataJson = Uri.encode(Gson().toJson(data))
                        navController.navigate(Screens.Detail.createRoute(dataJson))
                    },
                    goToSearch = { navController.navigate(Screens.Search.route) }
                )
            }
            composable(Screens.Favorite.route) {
                FavoriteScreen()
            }
            composable(Screens.Search.route) {
                SearchScreen(
                    onClickItem = { data ->
                        val dataJson = Uri.encode(Gson().toJson(data))
                        // ** add popbackstack, so if back we in home
                        navController.popBackStack()
                        navController.navigate(Screens.Detail.createRoute(dataJson))
                    }
                )
            }
            composable(
                route = Screens.Detail.route,
                arguments = listOf(
                    navArgument("data") {
                        // ** custom navtype for parcelable object
                        type = NewsModelType()
                    }
                )
            ) {
                val data = it.arguments?.getParcelable<NewsModel>("data") ?: NewsModel("")
                DetailScreen(
                    data = data,
                    navigateBack = {
                        navController.navigateUp()
                    },
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