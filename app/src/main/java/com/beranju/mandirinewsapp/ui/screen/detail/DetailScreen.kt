package com.beranju.mandirinewsapp.ui.screen.detail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import coil.compose.AsyncImage
import com.beranju.mandirinewsapp.R
import com.beranju.mandirinewsapp.domain.model.NewsModel
import com.beranju.mandirinewsapp.ui.theme.MandiriNewsAppTheme
import com.beranju.mandirinewsapp.ui.theme.Poppins
import com.beranju.mandirinewsapp.utils.convertDate
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailScreen(
    data: NewsModel,
    navigateBack: () -> Unit = {},
    viewModel: DetailViewModel = koinViewModel(),
    modifier: Modifier = Modifier
) {
    LaunchedEffect(Unit){
        viewModel.isFavoriteNews(data.publishedAt)
    }
//    viewModel.isFavoriteNews(data.id)
    val state = viewModel.uiState.collectAsState(initial = false)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        DetailHeader(
            title = data.title.toString(),
            url = data.url.toString(),
            isFavorite = state.value,
            navigateBack = navigateBack,
            onFavoriteClick = {
                if (state.value){
                    viewModel.setFavoriteNews(news = data, false)
                }else{
                    viewModel.setFavoriteNews(news = data, true)
                }
            }
        )
        Spacer(modifier = Modifier.height(25.dp))
        DetailContent(
            data.source?.name.toString(),
            data.title.toString(),
            data.author ?: stringResource(R.string.unknown),
            data.publishedAt.convertDate().toString(),
            data.urlToImage.toString(),
            data.description ?: stringResource(R.string.empty_news_description),
            data.url.toString()
        )
    }
}

@Composable
fun DetailHeader(
    title: String,
    url: String,
    isFavorite: Boolean,
    navigateBack: () -> Unit,
    onFavoriteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth()
    ) {
        IconButton(
            onClick = {
                navigateBack()
            }
        ) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
        }
        Row {
            IconButton(
                onClick = {
                    onFavoriteClick()
                }
            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = null,
                    tint = if (isFavorite) Color.Red else Color.Black
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            IconButton(
                onClick = {
                    val intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_SUBJECT, title)
                        putExtra(Intent.EXTRA_TEXT, "Baca berita selengkapnya di sini $url")
                        type = "text/plain"
                    }
                    val shareNews = Intent.createChooser(intent, null)
                    context.startActivity(shareNews)
                }
            ) {
                Icon(imageVector = Icons.Default.Share, contentDescription = null)
            }
        }

    }

}

@Composable
fun DetailContent(
    sourceName: String,
    title: String,
    author: String,
    publishAt: String,
    imageUrl: String,
    content: String,
    url: String,
    context: Context = LocalContext.current,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(text = sourceName)
        Text(
            text = title,
            style = MaterialTheme.typography.h1
        )
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(imageVector = Icons.Default.Person, contentDescription = null)
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = author,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(text = publishAt)

        }
        Spacer(modifier = Modifier.height(10.dp))
        if (imageUrl == "null") {
            Image(
                painter = painterResource(id = R.drawable.ic_happy_music),
                contentDescription = null
            )
        } else {
            AsyncImage(
                model = imageUrl,
                contentDescription = "thumbnail",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = content,
            style = TextStyle(
                fontFamily = Poppins,
                fontSize = 14.sp
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                context.startActivity(intent)

            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Read More")
        }

    }

}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    MandiriNewsAppTheme {
        DetailScreen(NewsModel(""))
    }

}