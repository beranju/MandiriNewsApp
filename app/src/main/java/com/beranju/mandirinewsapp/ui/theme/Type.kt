package com.beranju.mandirinewsapp.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.beranju.mandirinewsapp.R

/**
 * init custom font family
 * ref => https://mjmanaog.medium.com/jetpack-compose-tips-how-to-change-font-738917e46f10
 */
val Poppins = FontFamily(
    Font(R.font.poppins_bold, FontWeight.Bold),
    Font(R.font.poppins_semibold, FontWeight.Medium),
    Font(R.font.poppins_regular),
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    h1 = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    h2 = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)

