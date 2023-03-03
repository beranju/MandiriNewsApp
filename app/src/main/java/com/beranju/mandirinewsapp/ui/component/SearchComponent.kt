package com.beranju.mandirinewsapp.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.beranju.mandirinewsapp.ui.theme.MandiriNewsAppTheme

@Composable
fun SearchComponent(
    query: String,
    onChangeValue: (String) -> Unit = {},
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ){
        OutlinedTextField(
            value = query,
            onValueChange = onChangeValue,
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

@Preview(showBackground = true)
@Composable
fun SearchComponentPreview() {
    MandiriNewsAppTheme {
        SearchComponent("buku")
    }

}