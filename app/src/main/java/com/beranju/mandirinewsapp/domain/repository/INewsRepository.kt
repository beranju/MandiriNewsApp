package com.beranju.mandirinewsapp.domain.repository

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
}