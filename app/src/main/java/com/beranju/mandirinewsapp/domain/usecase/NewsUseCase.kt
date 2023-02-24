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
}