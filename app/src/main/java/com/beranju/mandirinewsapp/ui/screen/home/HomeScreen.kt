package com.beranju.mandirinewsapp.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        HeadLineSection()
        AllNewsSection()

    }

}

@Composable
fun AllNewsSection() {

}

@Composable
fun HeadLineSection(
    modifier: Modifier = Modifier
) {

}
