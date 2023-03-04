package com.beranju.mandirinewsapp.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.beranju.mandirinewsapp.R
import com.beranju.mandirinewsapp.domain.model.NewsModel
import com.beranju.mandirinewsapp.ui.common.UiState
import com.beranju.mandirinewsapp.ui.component.*
import com.beranju.mandirinewsapp.ui.theme.Poppins
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
    goToSearch: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel()
) {
    Scaffold(
        topBar = { HeaderHome(goToSearch) },
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            viewModel.headlineState.collectAsState(initial = UiState.Loading).value.let {
                when (it) {
                    is UiState.Loading -> {
                        viewModel.fetchHeadlineNews()
                        LazyRow(
                            modifier = modifier
                                .height(300.dp)
                                .padding(start = 16.dp, top = 16.dp)
                        ) {
                            repeat(2) {
                                item {
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
                when (it) {
                    is UiState.Loading -> {
                        viewModel.fetchAllNews()
                        LazyColumn(
                            modifier = modifier
                                .height(400.dp)
                                .padding(16.dp)
                        ) {
                            repeat(4) {
                                item {
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
                            onClickItem = onClickItem,
                        )
                    }
                }
            }


        }

    }


}

@Composable
fun HeaderHome(
    goToSearch: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Text(
                text = "Hello Beranju ",
                style = TextStyle(
                    fontFamily = Poppins,
                    fontSize = 16.sp
                )
            )
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Rounded.Favorite,
                    contentDescription = null,
                    tint = Color.Red
                )
            }
        }
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
            modifier = Modifier
                .fillMaxWidth()
                .clickable { goToSearch() }
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
            .height(500.dp)
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.text_semua_berita),
            fontSize = 18.sp,
            modifier = Modifier
                .padding(bottom = 16.dp)
        )
        LazyColumn {
            items(itemNews) { news ->
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
            .height(300.dp)
            .padding(start = 16.dp, top = 16.dp)
    ) {
        items(itemNews!!) { news ->
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
