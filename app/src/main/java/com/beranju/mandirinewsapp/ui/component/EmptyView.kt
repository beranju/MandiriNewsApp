package com.beranju.mandirinewsapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.beranju.mandirinewsapp.R

@Composable
fun EmptyView() {
    /**
     * basic usage of lottie in compose
     * https://github.com/airbnb/lottie/blob/master/android-compose.md
     */

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.emptybox))
    val progress by animateLottieCompositionAsState(composition = composition)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(210.dp)
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(composition = composition, progress = progress)
        Text(
            text = "Oopss, nothing found!",
            modifier = Modifier
                .padding(top = 8.dp)
        )
    }
}