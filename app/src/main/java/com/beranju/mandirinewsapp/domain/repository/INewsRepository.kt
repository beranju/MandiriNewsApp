package com.beranju.mandirinewsapp.domain.repository

import com.beranju.mandirinewsapp.domain.common.Resource
import com.beranju.mandirinewsapp.domain.model.NewsModel
import kotlinx.coroutines.flow.Flow

interface INewsRepository {
    fun fetchHeadlineNews(): Flow<Resource<List<NewsModel>>>
    fun fetchEverythingNews(): Flow<Resource<List<NewsModel>>>
}