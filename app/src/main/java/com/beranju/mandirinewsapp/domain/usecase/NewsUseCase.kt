package com.beranju.mandirinewsapp.domain.usecase

import com.beranju.mandirinewsapp.domain.common.Resource
import com.beranju.mandirinewsapp.domain.model.NewsModel
import kotlinx.coroutines.flow.Flow

/**
 * this interface created to make ui layer depend to domain layer
 */
interface NewsUseCase {
    fun fetchHeadlineNews(): Flow<Resource<List<NewsModel>>>
    fun fetchEverythingNews(): Flow<Resource<List<NewsModel>>>
    fun fetchNewsByQuery(query: String): Flow<Resource<List<NewsModel>>>
    fun getAllFavoriteNews(): Flow<List<NewsModel>>
    suspend fun setFavoriteNews(newsModel: NewsModel, state: Boolean)
    suspend fun isFavoriteNews(publishedAt: String): Boolean
}