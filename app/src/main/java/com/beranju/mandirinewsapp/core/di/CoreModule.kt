package com.beranju.mandirinewsapp.core.di

import androidx.room.Room
import com.beranju.mandirinewsapp.BuildConfig
import com.beranju.mandirinewsapp.core.local.room.NewsDatabase
import com.beranju.mandirinewsapp.core.remote.retrofit.ApiService
import com.beranju.mandirinewsapp.core.repositoty.NewsRepository
import com.beranju.mandirinewsapp.domain.repository.INewsRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * this variable is koin implementation in dependency injection
 *
 * single mean singleton or create once and use again when needed
 *
 */

val databaseModule = module {
    factory { get<NewsDatabase>().newsDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            NewsDatabase::class.java,
            "newsdb"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(
                if (BuildConfig.DEBUG) HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                else HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE))
            .readTimeout(120, TimeUnit.SECONDS)
            .connectTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single <INewsRepository> { NewsRepository(get(), get()) }
}