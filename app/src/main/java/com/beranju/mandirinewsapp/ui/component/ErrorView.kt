package com.beranju.mandirinewsapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.RenderMode
import com.airbnb.lottie.compose.*
import com.beranju.mandirinewsapp.R
import com.beranju.mandirinewsapp.ui.theme.Poppins

@Composable
fun ErrorView() {
    /**
     * basic usage of lottie in compose
     * https://github.com/airbnb/lottie/blob/master/android-compose.md
     */

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.error))
    val progress by animateLottieCompositionAsState(composition = composition, iterations = LottieConstants.IterateForever)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(
            composition = composition,
            progress = progress,
        )
        Text(
            text = "Oopss, something wrong",
            style = TextStyle(
                fontFamily = Poppins,
                fontSize = 16.sp
            ),
            modifier = Modifier
                .padding(top = 8.dp)
        )
    }
}