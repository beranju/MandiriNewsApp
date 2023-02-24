package com.beranju.mandirinewsapp.domain.model

import com.beranju.mandirinewsapp.core.remote.response.Source
import com.google.gson.annotations.SerializedName

data class NewsModel(
    val id: Int,
    val publishedAt: String? = null,
    val author: String? = null,
    val urlToImage: String? = null,
    val description: String? = null,
    val source: SourceModel? = null,
    val title: String? = null,
    val url: String? = null,
    val content: String? = null
)