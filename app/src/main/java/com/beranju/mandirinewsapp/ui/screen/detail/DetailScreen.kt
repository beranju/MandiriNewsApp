package com.beranju.mandirinewsapp.ui.screen.detail

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.beranju.mandirinewsapp.R
import com.beranju.mandirinewsapp.domain.model.NewsModel
import com.beranju.mandirinewsapp.ui.theme.MandiriNewsAppTheme
import com.beranju.mandirinewsapp.utils.convertDate

@Composable
fun DetailScreen(
    data: NewsModel,
    navigateBack: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
            .padding(16.dp)
    ) {
        DetailHeader(
            title = data.title.toString(),
            url = data.url.toString(),
            navigateBack = navigateBack)
        Spacer(modifier = Modifier.height(25.dp))
        DetailContent(
            data.source?.name.toString(),
            data.title.toString(),
            data.author ?: stringResource(R.string.unknown),
            data.publishedAt?.convertDate().toString(),
            data.urlToImage.toString(),
            data.description ?: stringResource(R.string.empty_news_description)
        )
    }
}

@Composable
fun DetailHeader(
    title: String,
    url: String,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth()
    ) {
        OutlinedButton(
            onClick = {
                      navigateBack()
            },
            shape = CircleShape
        ) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
        }
        Row {
            OutlinedButton(
                onClick = {},
                shape = CircleShape
            ) {
                Icon(imageVector = Icons.Default.Favorite, contentDescription = null)
            }
            Spacer(modifier = Modifier.width(10.dp))
            OutlinedButton(
                onClick = {
                    val intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_SUBJECT, title)
                        putExtra(Intent.EXTRA_TEXT, "Baca berita selengkapnya di sini $url")
                        type = "text/plain"
                    }
                    val shareNews = Intent.createChooser(intent, null)
                    context.startActivity(shareNews)
                },
                shape = CircleShape
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
        if (imageUrl == "null"){
            Image(painter = painterResource(id = R.drawable.ic_happy_music), contentDescription = null)
        }else{
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
            text = content
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {} ,
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
        DetailScreen(NewsModel(1))
    }

}