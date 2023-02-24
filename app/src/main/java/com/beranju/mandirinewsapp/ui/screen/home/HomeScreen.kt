package com.beranju.mandirinewsapp.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.beranju.mandirinewsapp.R
import com.beranju.mandirinewsapp.domain.model.NewsModel
import com.beranju.mandirinewsapp.ui.common.UiState
import com.beranju.mandirinewsapp.ui.component.AllNewsItem
import com.beranju.mandirinewsapp.ui.component.HeadlineNewsItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel()
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        viewModel.headlineState.collectAsState(initial = UiState.Loading).value.let {
            when(it){
                is UiState.Loading -> {
                    viewModel.fetchHeadlineNews()
                }
                is UiState.Empty -> {}
                is UiState.Error -> {}
                is UiState.Success -> {
                    HeadLineSection(
                        itemNews = it.data
                    )
                }
            }
        }
        viewModel.allNewsState.collectAsState(initial = UiState.Loading).value.let {
            when(it){
                is UiState.Loading -> {
                    viewModel.fetchAllNews()
                }
                is UiState.Empty -> {}
                is UiState.Error -> {}
                is UiState.Success -> {
                    AllNewsSection(
                        itemNews = it.data
                    )
                }
            }
        }


    }

}

@Composable
fun AllNewsSection(
    itemNews: List<NewsModel>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.text_semua_berita),
            fontSize = 18.sp,
            modifier = Modifier
                .padding(bottom = 16.dp)
        )
        LazyColumn{
            items(itemNews){news->
                AllNewsItem(
                    image = news.urlToImage.toString(),
                    title = news.title ?: stringResource(R.string.text_unknown),
                    author = news.author ?: stringResource(R.string.text_unknown),
                    publishAt = news.publishedAt ?: stringResource(R.string.text_unknown))
            }
        }
    }


}

@Composable
fun HeadLineSection(
    itemNews: List<NewsModel>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier
            .padding(start = 16.dp, top = 16.dp)
    ) {
        items(itemNews){news->
            HeadlineNewsItem(
                image = news.urlToImage.toString(),
                title = news.title ?: stringResource(R.string.text_unknown),
                sourceName = news.source?.name ?: stringResource(R.string.text_unknown),
                publishAt = news.publishedAt ?: stringResource(R.string.text_unknown))
        }
    }


}
