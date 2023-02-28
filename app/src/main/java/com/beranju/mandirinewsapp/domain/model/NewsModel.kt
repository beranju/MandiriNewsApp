package com.beranju.mandirinewsapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class NewsModel(
    val id: Int,
    val publishedAt: String? = null,
    val author: String? = null,
    val urlToImage: String? = null,
    val description: String? = null,
    val source: @RawValue SourceModel? = null,
    val title: String? = null,
    val url: String? = null,
    val content: String? = null
): Parcelable
