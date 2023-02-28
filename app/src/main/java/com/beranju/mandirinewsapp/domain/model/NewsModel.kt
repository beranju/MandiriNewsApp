package com.beranju.mandirinewsapp.domain.model

import android.os.Parcelable
import com.beranju.mandirinewsapp.core.remote.response.Source
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

/**
 * this class created to make data layer and view layer depend to domain layer
 *
 * Json class is annotation of moshi library to help pass object in navigation component as jsonString
 *
 *
 */

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
