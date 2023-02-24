package com.beranju.mandirinewsapp

import android.app.Application
import com.beranju.mandirinewsapp.core.di.networkModule
import com.beranju.mandirinewsapp.core.di.repositoryModule
import com.beranju.mandirinewsapp.di.newsUseCaseModule
import com.beranju.mandirinewsapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class NewsApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@NewsApp)
            koin.loadModules(
                listOf(
                    networkModule,
                    repositoryModule,
                    newsUseCaseModule,
                    viewModelModule
                )
            )
        }
    }
}