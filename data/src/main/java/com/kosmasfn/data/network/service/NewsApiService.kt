package com.kosmasfn.data.network.service

import com.kosmasfn.data.model.NewsDataModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Kosmas on October 09, 2023
 */
interface NewsApiService {

    @GET("sources")
    suspend fun getCategory(): Response<NewsDataModel?>?

    @GET("sources")
    suspend fun getSourcesByCategory(
        @Query("category") category: String,
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int
    ): Response<NewsDataModel?>?

    @GET("top-headlines")
    suspend fun getSources(
        @Query("sources") sources: String
    ): Response<NewsDataModel?>?

    @GET("everything")
    suspend fun getArticle(
        @Query("q") query: String,
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int,
    ): Response<NewsDataModel?>?
}