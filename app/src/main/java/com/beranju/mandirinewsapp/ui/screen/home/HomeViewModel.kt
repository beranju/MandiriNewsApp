package com.beranju.mandirinewsapp.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.beranju.mandirinewsapp.domain.common.Resource
import com.beranju.mandirinewsapp.domain.model.NewsModel
import com.beranju.mandirinewsapp.domain.usecase.NewsUseCase
import com.beranju.mandirinewsapp.ui.common.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val newsUseCase: NewsUseCase) : ViewModel() {

    private var _headlineState: MutableStateFlow<UiState<List<NewsModel>>> =
        MutableStateFlow(UiState.Loading)
    val headlineState: StateFlow<UiState<List<NewsModel>>> get() = _headlineState

    val allNews: Flow<PagingData<NewsModel>> = newsUseCase.fetchEverythingNews()

    fun fetchHeadlineNews() {
        viewModelScope.launch {
            newsUseCase.fetchHeadlineNews()
                .catch {
                    _headlineState.value = UiState.Error(it.localizedMessage!!)
                }
                .collect { result ->
                    when (result) {
                        is Resource.Loading -> {
                            _headlineState.value = UiState.Loading
                        }
                        is Resource.Empty -> {
                            _headlineState.value = UiState.Empty
                        }
                        is Resource.Error -> {
                            _headlineState.value = UiState.Error(result.message)
                        }
                        is Resource.Success -> {
                            _headlineState.value = UiState.Success(result.data)
                        }
                    }
                }
        }
    }
}