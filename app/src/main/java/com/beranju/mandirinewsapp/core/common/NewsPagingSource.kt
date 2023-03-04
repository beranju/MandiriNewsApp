package com.beranju.mandirinewsapp.core.common

import android.provider.ContactsContract.Data
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.beranju.mandirinewsapp.core.remote.response.ArticlesItem
import com.beranju.mandirinewsapp.core.remote.retrofit.ApiService
import com.beranju.mandirinewsapp.domain.model.NewsModel
import com.beranju.mandirinewsapp.utils.DataMapper

class NewsPagingSource(private val apiService: ApiService): PagingSource<Int, NewsModel>() {

    companion object{
        const val INITIAL_PAGE_INDEX = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsModel> {
        return try {
            val position = params.key ?: INITIAL_PAGE_INDEX
            val newsData = apiService.fetchEveryNews(params.loadSize, position).body()?.articles
            val modelData = DataMapper.mapResponseToModel(newsData!!)

            LoadResult.Page(
                data = modelData,
                prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                nextKey =  if (modelData.isEmpty()) null else position + 1
            )
        }catch (e: Exception){
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, NewsModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}