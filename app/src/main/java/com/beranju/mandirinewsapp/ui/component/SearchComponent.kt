package com.beranju.mandirinewsapp.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.beranju.mandirinewsapp.ui.theme.MandiriNewsAppTheme
import com.beranju.mandirinewsapp.ui.theme.Poppins

@Composable
fun SearchComponent(
    query: String,
    onChangeValue: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = query,
        onValueChange = onChangeValue,
        placeholder = {
            Text(
                text = "Find News around the world",
                style = TextStyle(
                    fontFamily = Poppins,
                    fontSize = 18.sp
                )
            )
        },
        maxLines = 1,
        textStyle = TextStyle(
            fontFamily = Poppins,
            fontSize = 18.sp
        ),
        shape = RoundedCornerShape(16.dp),
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = null)
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
fun SearchComponentPreview() {
    MandiriNewsAppTheme {
        SearchComponent("buku")
    }

}