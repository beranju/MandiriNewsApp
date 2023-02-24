package com.beranju.mandirinewsapp.utils

import com.beranju.mandirinewsapp.core.remote.response.ArticlesItem
import com.beranju.mandirinewsapp.core.remote.response.Source
import com.beranju.mandirinewsapp.domain.model.NewsModel
import com.beranju.mandirinewsapp.domain.model.SourceModel
import kotlin.random.Random

object DataMapper {
    fun mapResponseToModel(input: List<ArticlesItem>): List<NewsModel>{
        val newsList = ArrayList<NewsModel>()
        val id = Random.nextInt()
        input.map {
            val news = NewsModel(
                id = id,
                publishedAt = it.publishedAt,
                author = it.author,
                urlToImage = it.urlToImage,
                description = it.description,
                source = it.source.let { data -> SourceModel(data?.name, data?.id) },
                title = it.title,
                url = it.url,
                content = it.content
            )
            newsList.add(news)
        }
        return newsList
    }
}