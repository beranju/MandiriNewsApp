package com.beranju.mandirinewsapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.beranju.mandirinewsapp.ui.screen.home.HomeScreen
import com.beranju.mandirinewsapp.ui.theme.MandiriNewsAppTheme

@Composable
fun BaseScreen(
    modifier: Modifier = Modifier
) {
    HomeScreen()
    
}

@Preview(showBackground = true)
@Composable
fun BaseScreenPreview() {
    MandiriNewsAppTheme {
        BaseScreen()
    }
    
}