package com.kosmasfn.data.repository

import com.kosmasfn.data.model.NewsDataModel
import com.kosmasfn.data.network.service.NewsApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import javax.inject.Inject

/**
 * Created by Kosmas on October 09, 2023
 */
class NewsRepositoryImpl @Inject constructor(
    private val apiService: NewsApiService
) : NewsRepository {

    override suspend fun getCategory(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<NewsDataModel> = flow {
        val response = apiService.getCategory()
        try {
            if (response?.isSuccessful == true) {
                response.body()?.let { emit(it) }
            } else {
                onError(response?.message())
            }
        } catch (ex: java.lang.Exception) {
            onError(ex.message)
        }
    }.catch {
        onError(it.message)
    }.onCompletion { onComplete() }

    override suspend fun getSourcesByCategory(
        category: String,
        page: Int,
        pageSize: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<NewsDataModel> = flow {
        val response = apiService.getSourcesByCategory(category, pageSize, page)
        try {
            if (response?.isSuccessful == true) {
                response.body()?.let { emit(it) }
            } else {
                onError(response?.message())
            }
        } catch (ex: java.lang.Exception) {
            onError(ex.message)
        }
    }.catch {
        onError(it.message)
    }.onCompletion { onComplete() }

    override suspend fun getSources(
        sources: String,
        page: Int,
        pageSize: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<NewsDataModel> = flow {
        val response = apiService.getSources(sources)
        try {
            if (response?.isSuccessful == true) {
                response.body()?.let { emit(it) }
            } else {
                onError(response?.message())
            }
        } catch (ex: java.lang.Exception) {
            onError(ex.message)
        }
    }.catch {
        onError(it.message)
    }.onCompletion { onComplete() }

    override suspend fun getArticle(
        source: String,
        page: Int,
        pageSize: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<NewsDataModel> = flow {
        val response = apiService.getArticle(source, pageSize, page)
        try {
            if (response?.isSuccessful == true) {
                response.body()?.let { emit(it) }
            } else {
                onError(if (response?.code() == 429) "API Limit 100 Hit 24 Hours" else response?.message())
            }
        } catch (ex: java.lang.Exception) {
            onError(ex.message)
        }
    }.catch {
        onError(it.message)
    }.onCompletion { onComplete() }
}