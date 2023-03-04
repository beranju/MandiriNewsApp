package com.beranju.mandirinewsapp.ui.screen.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beranju.mandirinewsapp.core.repositoty.NewsRepository
import com.beranju.mandirinewsapp.domain.model.NewsModel
import com.beranju.mandirinewsapp.domain.usecase.NewsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val newsUseCase: NewsUseCase): ViewModel() {
    private var _uiState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val uiState: StateFlow<Boolean> get() = _uiState


    suspend fun isFavoriteNews(publishedAt: String){
            _uiState.value = newsUseCase.isFavoriteNews(publishedAt)
    }

    fun setFavoriteNews(news: NewsModel, state:Boolean){
        viewModelScope.launch {
            newsUseCase.setFavoriteNews(news, state)
            isFavoriteNews(news.publishedAt)
        }
    }

}