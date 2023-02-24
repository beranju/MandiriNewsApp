package com.beranju.mandirinewsapp.domain.usecase

import com.beranju.mandirinewsapp.core.repositoty.NewsRepository
import com.beranju.mandirinewsapp.domain.common.Resource
import com.beranju.mandirinewsapp.domain.model.NewsModel
import com.beranju.mandirinewsapp.domain.repository.INewsRepository
import kotlinx.coroutines.flow.Flow

class NewsInteractor(private val newsRepository: INewsRepository): NewsUseCase {
    override fun fetchHeadlineNews(): Flow<Resource<List<NewsModel>>> = newsRepository.fetchHeadlineNews()

    override fun fetchEverythingNews(): Flow<Resource<List<NewsModel>>> = newsRepository.fetchEverythingNews()
}