package com.beranju.mandirinewsapp.domain.usecase

import com.beranju.mandirinewsapp.core.repositoty.NewsRepository
import com.beranju.mandirinewsapp.domain.common.Resource
import com.beranju.mandirinewsapp.domain.model.NewsModel
import com.beranju.mandirinewsapp.domain.repository.INewsRepository
import kotlinx.coroutines.flow.Flow

/**
 * implementation of interface newsusecase
 */
class NewsInteractor(private val newsRepository: INewsRepository): NewsUseCase {
    override fun fetchHeadlineNews(): Flow<Resource<List<NewsModel>>> = newsRepository.fetchHeadlineNews()

    override fun fetchEverythingNews(): Flow<Resource<List<NewsModel>>> = newsRepository.fetchEverythingNews()

    override fun fetchNewsByQuery(query: String): Flow<Resource<List<NewsModel>>> = newsRepository.fetchNewsByQuery(query)

    override fun getAllFavoriteNews(): Flow<List<NewsModel>> = newsRepository.getAllFavoriteNews()

    override suspend fun setFavoriteNews(newsModel: NewsModel, state: Boolean) = newsRepository.setFavoriteNews(newsModel, state)

    override suspend fun isFavoriteNews(publishedAt: String): Boolean = newsRepository.isFavorite(publishedAt)
}