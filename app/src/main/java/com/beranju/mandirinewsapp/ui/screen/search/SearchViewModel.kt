package com.beranju.mandirinewsapp.ui.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beranju.mandirinewsapp.domain.common.Resource
import com.beranju.mandirinewsapp.domain.model.NewsModel
import com.beranju.mandirinewsapp.domain.usecase.NewsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class SearchViewModel(private val newsUseCase: NewsUseCase): ViewModel() {

    private var _uiState: MutableStateFlow<SearchState<List<NewsModel>>> = MutableStateFlow(SearchState.Init)
    val uiState: StateFlow<SearchState<List<NewsModel>>> get() =  _uiState

    private var _searchQuery: MutableStateFlow<String> = MutableStateFlow("")
    val searchQuery : StateFlow<String> get() = _searchQuery

    fun fetchNewsByQuery(query: String){
        viewModelScope.launch {
            newsUseCase.fetchNewsByQuery(query)
                .catch {
                    _uiState.value = SearchState.Error(it.message.toString())
                }
                .collect{result ->
                    when(result){
                        is Resource.Loading -> _uiState.value = SearchState.Loading
                        is Resource.Empty -> _uiState.value = SearchState.Empty
                        is Resource.Error -> _uiState.value = SearchState.Error(result.message)
                        is Resource.Success -> _uiState.value = SearchState.Success(result.data)
                    }
                }
        }
    }

    fun onQueryChange(query: String){
        _searchQuery.value = query
        if (query.isNotEmpty()){
            fetchNewsByQuery(searchQuery.value)
        }
    }
}

sealed class SearchState<out T: Any>{
    data class Success<out T : Any>(val data: T) : SearchState<T>()
    data class Error(val message: String) : SearchState<Nothing>()
    object Init: SearchState<Nothing>()
    object Loading : SearchState<Nothing>()
    object Empty: SearchState<Nothing>()
}