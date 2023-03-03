package com.beranju.mandirinewsapp.ui.screen.search


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.beranju.mandirinewsapp.R
import com.beranju.mandirinewsapp.domain.model.NewsModel
import com.beranju.mandirinewsapp.ui.component.*
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = koinViewModel()
) {
    val query = viewModel.searchQuery.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        SearchComponent(
            query = query.value,
            onChangeValue = {
                viewModel.onQueryChange(it)
            }

        )
        Spacer(modifier = Modifier.height(16.dp))
        viewModel.uiState.collectAsState().value.let {
            when (it) {
                is SearchState.Init -> {
                    InitView()
                }
                is SearchState.Loading -> {
                    LazyColumn{
                        repeat(5){
                            item {
                                AllNewsShimmerAnimation()
                            }
                        }
                    }
                }
                is SearchState.Empty -> {
                    EmptyView()
                }
                is SearchState.Error -> {
                    ErrorView()
                }
                is SearchState.Success -> {
                    SearchContent(it.data)
                }
            }
        }
    }
}

@Composable
fun SearchContent(
    data: List<NewsModel>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth()
    ) {
        items(data) { news ->
            AllNewsItem(
                image = news.urlToImage.toString(),
                title = news.title ?: stringResource(id = R.string.unknown),
                author = news.author ?: stringResource(id = R.string.unknown),
                publishAt = news.publishedAt ?: stringResource(id = R.string.unknown)
            )
        }
    }

}
