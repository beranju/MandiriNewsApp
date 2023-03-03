package com.beranju.mandirinewsapp.ui.screen.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.beranju.mandirinewsapp.ui.component.SearchComponent

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Spacer(modifier = Modifier.height(16.dp))
        SearchComponent(query = "")
        LazyColumn{

        }
    }


}