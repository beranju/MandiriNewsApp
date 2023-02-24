package com.beranju.mandirinewsapp.core.repositoty

import android.util.Log
import com.beranju.mandirinewsapp.core.remote.retrofit.ApiService
import com.beranju.mandirinewsapp.domain.common.Resource
import com.beranju.mandirinewsapp.domain.model.NewsModel
import com.beranju.mandirinewsapp.domain.repository.INewsRepository
import com.beranju.mandirinewsapp.utils.DataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class NewsRepository(
    private val apiService: ApiService
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
                        Log.d("NewsRepository", "${data.articles}")
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
                        Log.d("NewsRepository", "${data.articles}")
                    }
                }else{
                    emit(Resource.Error(response.message()))
                }
            }catch (e: Exception){
                emit(Resource.Error(e.localizedMessage!!))
            }
        }.flowOn(Dispatchers.IO)
}