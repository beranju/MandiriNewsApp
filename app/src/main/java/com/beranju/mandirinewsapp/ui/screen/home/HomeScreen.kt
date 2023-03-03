package com.beranju.mandirinewsapp.ui.screen.home

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.beranju.mandirinewsapp.R
import com.beranju.mandirinewsapp.domain.model.NewsModel
import com.beranju.mandirinewsapp.ui.common.UiState
import com.beranju.mandirinewsapp.ui.component.*
import org.koin.androidx.compose.koinViewModel

/**
 * infinite scrolling ref: https://dev.to/luismierez/infinite-lazycolumn-in-jetpack-compose-44a4
 * horizontal list with indicator ref : https://google.github.io/accompanist/pager/
 * animated list => https://medium.com/@andreclassen1337/create-android-compose-lazylist-scroll-effects-af5a423a53e6
 * infinte auto scroll => https://medium.com/canopas/android-infinite-auto-scroll-with-jetpack-compose-ef8d573f8878
 *
 * paged list compose => https://medium.com/@manavtamboli/infinite-list-paged-list-in-jetpack-compose-b10fc7e74768
 */

@Composable
fun HomeScreen(
    onClickItem: (NewsModel) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel()
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        HeaderHome()
        Spacer(modifier = Modifier.height(20.dp))
        viewModel.headlineState.collectAsState(initial = UiState.Loading).value.let {
            when(it){
                is UiState.Loading -> {
                    viewModel.fetchHeadlineNews()
                    LazyRow(
                        modifier = modifier
                            .padding(start = 16.dp, top = 16.dp)
                    ){
                        repeat(2){
                            item{
                                HeadlineShimmerAnimation()
                            }
                        }
                    }
                }
                is UiState.Empty -> {
                    EmptyView()
                }
                is UiState.Error -> {

                }
                is UiState.Success -> {
                    HeadLineSection(
                        itemNews = it.data,
                        onClickItem = onClickItem
                    )
                }
            }
        }
        viewModel.allNewsState.collectAsState(initial = UiState.Loading).value.let {
            when(it){
                is UiState.Loading -> {
                    viewModel.fetchAllNews()
                    LazyColumn (
                        modifier = modifier
                            .padding(16.dp)
                    ){
                        repeat(4){
                            item{
                                AllNewsShimmerAnimation()
                            }
                        }
                    }
                }
                is UiState.Empty -> {
                    EmptyView()
                }
                is UiState.Error -> {}
                is UiState.Success -> {
                    AllNewsSection(
                        itemNews = it.data,
                        onClickItem = onClickItem
                    )
                }
            }
        }


    }

}

@Composable
fun HeaderHome(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ){
        OutlinedTextField(
            value = "",
            enabled = false,
            onValueChange = {},
            placeholder = {
                Text(text = "Find News around the world")
            },
            maxLines = 1,
            textStyle = MaterialTheme.typography.body1,
            shape = RoundedCornerShape(16.dp),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = null)
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun AllNewsSection(
    itemNews: List<NewsModel>,
    onClickItem: (NewsModel) -> Unit,
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
                    publishAt = news.publishedAt ?: stringResource(R.string.text_unknown),
                    modifier = Modifier.clickable {
                        onClickItem(news)
                    }
                )

            }
        }
    }

}

@Composable
fun HeadLineSection(
    itemNews: List<NewsModel>?,
    onClickItem: (NewsModel) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier
            .padding(start = 16.dp, top = 16.dp)
    ) {
        items(itemNews!!){news->
            HeadlineNewsItem(
                image = news.urlToImage,
                title = news.title ?: stringResource(R.string.text_unknown),
                sourceName = news.source?.name ?: stringResource(R.string.text_unknown),
                publishAt = news.publishedAt ?: stringResource(R.string.text_unknown),
                modifier = Modifier.clickable {
                    onClickItem(news)
                }
            )
        }
    }

}
