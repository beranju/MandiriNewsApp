package com.beranju.mandirinewsapp.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beranju.mandirinewsapp.domain.common.Resource
import com.beranju.mandirinewsapp.domain.model.NewsModel
import com.beranju.mandirinewsapp.domain.usecase.NewsUseCase
import com.beranju.mandirinewsapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(private val newsUseCase: NewsUseCase): ViewModel() {

    private var _headlineState: MutableStateFlow<UiState<List<NewsModel>>> = MutableStateFlow(UiState.Loading)
    val headlineState: StateFlow<UiState<List<NewsModel>>> get() = _headlineState

    private var _allNewsState: MutableStateFlow<UiState<List<NewsModel>>> = MutableStateFlow(UiState.Loading)
    val allNewsState: StateFlow<UiState<List<NewsModel>>> get() = _allNewsState

    fun fetchHeadlineNews(){
        viewModelScope.launch {
            newsUseCase.fetchHeadlineNews()
                .catch {
                    _headlineState.value = UiState.Error(it.localizedMessage!!)
                }
                .collect{result->
                    when(result){
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

    fun fetchAllNews(){
        viewModelScope.launch{
            newsUseCase.fetchEverythingNews()
                .catch {
                    _allNewsState.value = UiState.Error(it.localizedMessage!!)
                }
                .collect{result->
                    when(result){
                        is Resource.Loading -> {
                            _allNewsState.value = UiState.Loading
                        }
                        is Resource.Empty -> {
                            _allNewsState.value = UiState.Empty
                        }
                        is Resource.Error -> {
                            _allNewsState.value = UiState.Error(result.message)
                        }
                        is Resource.Success -> {
                            _allNewsState.value = UiState.Success(result.data)
                        }
                    }

                }
        }
    }
}