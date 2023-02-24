package com.beranju.mandirinewsapp.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.beranju.mandirinewsapp.ui.theme.MandiriNewsAppTheme

@Composable
fun HeadlineNewsItem(
    title: String,
    image: String,
    sourceName: String,
    publishAt: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(300.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        AsyncImage(
            model = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .clip(RoundedCornerShape(8.dp))
        )
        Text(
            text = title,
            overflow = TextOverflow.Ellipsis,
            fontSize = 18.sp,
            maxLines = 2,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Text(
                text = sourceName,
                fontSize = 12.sp
            )
            Text(
                text = publishAt,
                fontSize = 12.sp
            )
        }

    }

}

@Preview
@Composable
fun HeadlineNewsPreview() {
    MandiriNewsAppTheme {
        HeadlineNewsItem(title = "test", image = "test", sourceName = "test" , publishAt = "test" )
    }

}