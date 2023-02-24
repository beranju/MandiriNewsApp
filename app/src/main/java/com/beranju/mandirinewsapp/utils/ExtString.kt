package com.beranju.mandirinewsapp.utils

import java.text.SimpleDateFormat
import java.util.*

fun String.convertDate(): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
    val date = dateFormat.parse(this)
    val formatter = SimpleDateFormat("dd MMM yyyy")
    return formatter.format(date)
}