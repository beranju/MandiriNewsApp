package com.beranju.mandirinewsapp.domain.repository

import com.beranju.mandirinewsapp.core.local.entity.NewsEntity
import com.beranju.mandirinewsapp.domain.common.Resource
import com.beranju.mandirinewsapp.domain.model.NewsModel
import kotlinx.coroutines.flow.Flow

/**
 * to make data layer depends to domain layer
 * so it's implement mvvm
 */
interface INewsRepository {
    fun fetchHeadlineNews(): Flow<Resource<List<NewsModel>>>
    fun fetchEverythingNews(): Flow<Resource<List<NewsModel>>>
    fun fetchNewsByQuery(query: String): Flow<Resource<List<NewsModel>>>
    fun getAllFavoriteNews(): Flow<List<NewsModel>>
    suspend fun setFavoriteNews(news: NewsModel, state: Boolean)
    suspend fun isFavorite(publishedAt: String): Boolean
}