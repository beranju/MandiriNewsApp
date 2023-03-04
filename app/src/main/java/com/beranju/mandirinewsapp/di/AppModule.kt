package com.beranju.mandirinewsapp.di

import com.beranju.mandirinewsapp.domain.usecase.NewsInteractor
import com.beranju.mandirinewsapp.domain.usecase.NewsUseCase
import com.beranju.mandirinewsapp.ui.screen.detail.DetailHeader
import com.beranju.mandirinewsapp.ui.screen.detail.DetailViewModel
import com.beranju.mandirinewsapp.ui.screen.home.HomeViewModel
import com.beranju.mandirinewsapp.ui.screen.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val newsUseCaseModule = module {
    factory <NewsUseCase> { NewsInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}