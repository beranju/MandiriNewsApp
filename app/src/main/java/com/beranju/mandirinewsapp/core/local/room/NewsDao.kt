package com.beranju.mandirinewsapp.core.local.room

import androidx.room.*
import com.beranju.mandirinewsapp.core.local.entity.NewsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Query("SELECT * FROM news")
    fun getAllNews(): Flow<List<NewsEntity>>

    @Query("SELECT EXISTS(SELECT * FROM news WHERE id = :id)")
    suspend fun isNewsExists(id: Int): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(newsEntity: NewsEntity)

    @Delete
    suspend fun deleteNews(newsEntity: NewsEntity)
}