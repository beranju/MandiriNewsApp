package com.beranju.mandirinewsapp.ui.common

import com.beranju.mandirinewsapp.domain.common.Resource

sealed class UiState<out T: Any>{
    data class Success<out T : Any>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
    object Loading : UiState<Nothing>()
    object Empty: UiState<Nothing>()
}
