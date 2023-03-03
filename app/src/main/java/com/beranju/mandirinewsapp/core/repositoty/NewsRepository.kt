package com.beranju.mandirinewsapp.core.repositoty

import android.util.Log
import com.beranju.mandirinewsapp.core.local.room.NewsDao
import com.beranju.mandirinewsapp.core.remote.retrofit.ApiService
import com.beranju.mandirinewsapp.domain.common.Resource
import com.beranju.mandirinewsapp.domain.model.NewsModel
import com.beranju.mandirinewsapp.domain.repository.INewsRepository
import com.beranju.mandirinewsapp.utils.DataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * implementation of INewsRepository interface
 *
 * avoid use livedata from repository and recommend to use flow
 * each function flow on dispatcher.io because they fetch data from api and may need more time
 */
class NewsRepository(
    private val apiService: ApiService,
    private val newsDao: NewsDao
    ): INewsRepository {

    override fun fetchHeadlineNews(): Flow<Resource<List<NewsModel>>> =
        flow {
            emit(Resource.Loading)
            try {
                val response = apiService.fetchHeadlineNews(10, 1)
                if (response.isSuccessful){
                    val data = response.body()
                    if (data != null){
                        emit(Resource.Success(DataMapper.mapResponseToModel(data.articles)))
                    }else{
                        emit(Resource.Empty)
                    }
                }else{
                    emit(Resource.Error(response.message()))
                }
            }catch (e: Exception){
                emit(Resource.Error(e.localizedMessage!!))
            }
        }.flowOn(Dispatchers.IO)

    override fun fetchEverythingNews(): Flow<Resource<List<NewsModel>>> =
        flow {
            emit(Resource.Loading)
            try {
                val response = apiService.fetchEveryNews(20, 1)
                if (response.isSuccessful){
                    val data = response.body()
                    if (data == null){
                        emit(Resource.Empty)
                    }else{
                        emit(Resource.Success(DataMapper.mapResponseToModel(data.articles)))
                    }
                }else{
                    emit(Resource.Error(response.message()))
                }
            }catch (e: Exception){
                emit(Resource.Error(e.localizedMessage!!))
            }
        }.flowOn(Dispatchers.IO)

    override fun fetchNewsByQuery(query: String): Flow<Resource<List<NewsModel>>> =
        flow {
            emit(Resource.Loading)
            try {
                val response = apiService.fetchNewsByQuery(pageSize = 15, page = 1, query = query)
                if (response.isSuccessful){
                    val data = response.body()
                    if (data?.articles?.isEmpty() == true){
                        emit(Resource.Empty)
                    }
                    if (data?.articles?.isNotEmpty() == true){
                        emit(Resource.Success(DataMapper.mapResponseToModel(data.articles)))
                    }
                }else{
                    emit(Resource.Error(response.message()))
                }
            }catch (e: Exception){
                emit(Resource.Error(e.localizedMessage!!))
            }
        }.flowOn(Dispatchers.Default)
}