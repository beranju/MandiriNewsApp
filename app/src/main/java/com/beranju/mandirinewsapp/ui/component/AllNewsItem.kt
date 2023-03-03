package com.beranju.mandirinewsapp.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.beranju.mandirinewsapp.domain.model.NewsModel
import com.beranju.mandirinewsapp.ui.theme.MandiriNewsAppTheme
import com.beranju.mandirinewsapp.utils.convertDate

@Composable
fun AllNewsItem(
    image: String,
    title: String,
    author: String,
    publishAt: String,
    onClickItem : (NewsModel) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        /**
         * this is implementation of coil
         * to load an image like glide in compose
         */
        AsyncImage(
            model = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(110.dp)
                .clip(RoundedCornerShape(6.dp))
        )
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .height(110.dp)

        ) {
            Text(
                text = title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth()
            )
            Row(
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Text(
                    text = author,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 12.sp,
                    color = Color.DarkGray,
                    modifier = Modifier
                        .weight(2f)
                        .padding(start = 8.dp)
                )
                Text(
                    /**
                     * convertDate() is an extension function
                     * this function add to string
                     */
                    text = publishAt.convertDate(),
                    fontSize = 12.sp,
                    color = Color.DarkGray,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                )
            }
        }

    }

}

@Preview(showBackground = true)
@Composable
fun AllNewsItemPreview() {
    MandiriNewsAppTheme {
        AllNewsItem(image = "", title = "test", author = "test", publishAt = "Test")
    }
}