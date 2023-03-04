package com.beranju.mandirinewsapp.utils

import com.beranju.mandirinewsapp.core.local.entity.NewsEntity
import com.beranju.mandirinewsapp.core.remote.response.ArticlesItem
import com.beranju.mandirinewsapp.domain.model.NewsModel
import com.beranju.mandirinewsapp.domain.model.SourceModel
import kotlin.math.abs
import kotlin.random.Random

object DataMapper {
    fun mapResponseToModel(input: List<ArticlesItem>): List<NewsModel>{
        val newsList = ArrayList<NewsModel>()
        input.map {
            val news = NewsModel(
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

    fun entityToModel(input: List<NewsEntity>): List<NewsModel>{
        val newsList = ArrayList<NewsModel>()
        val id = Random.nextInt()
        input.map {
            val news = NewsModel(
                publishedAt = it.publishedAt,
                author = it.author,
                urlToImage = it.urlToImage,
                description = it.description,
                source = SourceModel(it.sourceName, null) ,
                title = it.title,
                url = it.url,
                content = it.content
            )
            newsList.add(news)
        }
        return newsList
    }

    fun modelToEntity(input: NewsModel): NewsEntity {
        return NewsEntity(
            publishedAt = input.publishedAt.toString(),
            author = input.author.toString(),
            urlToImage = input.urlToImage.toString(),
            description = input.description.toString(),
            sourceName = input.source?.name.toString(),
            title = input.title.toString(),
            url = input.url.toString(),
            content = input.content.toString()
        )
    }
}