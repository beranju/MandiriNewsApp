package com.beranju.mandirinewsapp.ui.common

import com.beranju.mandirinewsapp.domain.common.Resource

/**
 * this sealed class used to manage ui in screen
 */
sealed class UiState<out T: Any>{
    data class Success<out T : Any>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
    object Loading : UiState<Nothing>()
    object Empty: UiState<Nothing>()
}
