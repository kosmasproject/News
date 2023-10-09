package com.kosmasfn.data.localdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Created by Kosmas on October 09, 2023.
 */
@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveResult(newsEntity: NewsEntity)

    @Insert
    fun saveAll(users: Array<NewsEntity>)

    @get:Query("SELECT * FROM NewsEntity")
    val result: Array<NewsEntity>

    @Query("DELETE FROM NewsEntity where urlToImage = :url")
    fun deleteItemNews(url: String)

    @Query("SELECT * FROM NewsEntity where url = :url")
    fun findArticleOnLocal(url: String): List<NewsEntity>
}