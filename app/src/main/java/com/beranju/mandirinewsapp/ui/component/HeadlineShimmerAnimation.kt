package com.beranju.mandirinewsapp.ui.component

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.beranju.mandirinewsapp.ui.theme.ShimmerColorShades

@Composable
fun HeadlineShimmerAnimation(
) {
    /**
     * create infinite transition
     */

    val transition = rememberInfiniteTransition()
    val translateAnim by transition.animateFloat(
        // ** specify animation position
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            // ** tween animation beetwen values over duration milis
            tween(durationMillis = 2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val brush = Brush.linearGradient(
        colors = ShimmerColorShades,
        start = Offset(10f, 10f),
        end = Offset(translateAnim, translateAnim)
    )
    HeadlineShimmerItem(brush = brush)
    
}

@Composable
fun HeadlineShimmerItem(
    brush: Brush
) {
    Column(
        modifier = Modifier
            .width(300.dp)
            .clip(RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(brush)
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .padding(vertical = 4.dp)
                .background(brush = brush)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Spacer(
                modifier = Modifier
                    .height(30.dp)
                    .width(100.dp)
                    .background(brush = brush)
            )
            Spacer(
                modifier = Modifier
                    .width(70.dp)
                    .height(30.dp)
                    .background(brush = brush)
            )
        }

    }
}