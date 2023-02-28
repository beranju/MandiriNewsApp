package com.beranju.mandirinewsapp.ui.screen.detail

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.beranju.mandirinewsapp.domain.model.NewsModel
import com.beranju.mandirinewsapp.ui.theme.MandiriNewsAppTheme
import com.beranju.mandirinewsapp.utils.convertDate

@Composable
fun DetailScreen(
    data: NewsModel,
    modifier: Modifier = Modifier
) {
    Column {
        DetailHeader()
        Spacer(modifier = Modifier.height(10.dp))
        DetailContent(
            data.source?.name ?: return,
            data.title ?:  return,
            data.author ?: return,
            data.publishedAt?.convertDate() ?: return,
            data.urlToImage ?: return,
            data.description?: return
        )
    }

//    Scaffold(
//        topBar = {
//            TopAppBar {
//                Text(text = url)
//            }
//        }
//        ) {
//            Box{
////                AndroidView(factory = {
////                    WebView(it).apply {
////                        layoutParams = ViewGroup.LayoutParams(
////                            ViewGroup.LayoutParams.MATCH_PARENT,
////                            ViewGroup.LayoutParams.MATCH_PARENT,
////                        )
////                        webViewClient = WebViewClient()
////                        loadUrl(url)
////                    }
////                }, update = {
////                    it.loadUrl(url)
////                })
//                Row {
//                    Icon(imageVector = Icons.Default.Favorite, contentDescription = null)
//                    Icon(imageVector = Icons.Default.Share, contentDescription = null)
//                }
//            }
//        }
}

@Composable
fun DetailHeader(
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth()
    ) {
        OutlinedButton(
            onClick = {},
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
                onClick = {},
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
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
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
                text = author
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(text = publishAt)

        }
        Spacer(modifier = Modifier.height(10.dp))
        AsyncImage(
            model = imageUrl,
            contentDescription = "thumbnail",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(10.dp))
        )
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