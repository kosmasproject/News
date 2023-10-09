package com.kosmasfn.data.repository

import com.kosmasfn.data.model.NewsDataModel
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query

/**
 * Created by Kosmas on October 09, 2023
 */
interface NewsRepository {
    suspend fun getCategory(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<NewsDataModel>

    suspend fun getSourcesByCategory(
        @Query("category") category: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = 20,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<NewsDataModel>

    suspend fun getSources(
        @Query("sources") sources: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = 20,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<NewsDataModel>

    suspend fun getArticle(
        @Query("q") source: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = 20,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<NewsDataModel>
}