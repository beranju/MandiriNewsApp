package com.beranju.mandirinewsapp.core.remote.retrofit

import com.beranju.mandirinewsapp.BuildConfig
import com.beranju.mandirinewsapp.core.remote.response.EverythingResponse
import com.beranju.mandirinewsapp.core.remote.response.HeadLineResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * this interface use to define how to connect to api
 */
interface ApiService {

    @GET("top-headlines")
    @Headers("Authorization: apiKey ${BuildConfig.API_KEY}")
    suspend fun fetchHeadlineNews (
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int,
        @Query("country") country: String = "id"
    ):Response<HeadLineResponse>

    @GET("everything")
    @Headers("Authorization: apiKey ${BuildConfig.API_KEY}")
    suspend fun fetchEveryNews(
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int,
        @Query("domains") domains: String = "foxnews.com,cnbc.com,abcnews.go.com,bbc.com,bloomberg.com,cnn.com,techcrunch.com,thenextweb.com",
        @Query("sortBy") sortBy: String = "popularity",
    ): Response<EverythingResponse>
}