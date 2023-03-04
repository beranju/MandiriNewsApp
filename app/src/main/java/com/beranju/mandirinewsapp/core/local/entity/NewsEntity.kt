package com.beranju.mandirinewsapp.core.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.beranju.mandirinewsapp.domain.model.SourceModel
import kotlinx.parcelize.RawValue

@Entity(tableName = "news")
data class NewsEntity(
    @PrimaryKey
    val publishedAt: String,
    val author: String,
    val urlToImage: String,
    val description: String,
    val sourceName: String,
    val title: String,
    val url: String,
    val content: String
)
