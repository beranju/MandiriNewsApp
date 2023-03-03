package com.beranju.mandirinewsapp.ui.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beranju.mandirinewsapp.domain.usecase.NewsUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class SearchViewModel(private val newsUseCase: NewsUseCase): ViewModel() {

    fun fetchNewsByQuery(query: String){
        viewModelScope.launch {
            newsUseCase.fetchNewsByQuery(query)
                .catch {  }
                .collect()
        }
    }
}