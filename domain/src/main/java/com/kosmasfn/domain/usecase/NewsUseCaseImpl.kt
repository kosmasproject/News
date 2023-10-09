package com.kosmasfn.domain.usecase

import com.kosmasfn.domain.toDomainModel
import com.kosmasfn.domain.model.NewsDomainModel
import com.kosmasfn.data.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Created by Kosmas on October 09, 2023.
 */
class NewsUseCaseImpl(private val repository: NewsRepository) : NewsUseCase {

    override suspend fun getCategory(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<NewsDomainModel> =
        repository.getCategory(onStart, onComplete, onError).map { it.toDomainModel() }


    override suspend fun getSourcesByCategory(
        category: String,
        page: Int,
        pageSize: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<NewsDomainModel> =
        repository.getSourcesByCategory(category, page, pageSize, onStart, onComplete, onError)
            .map { it.toDomainModel() }

    override suspend fun getSources(
        sources: String,
        page: Int,
        pageSize: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<NewsDomainModel> =
        repository.getSources(sources, page, pageSize, onStart, onComplete, onError)
            .map { it.toDomainModel() }


    override suspend fun getArticle(
        source: String,
        page: Int,
        pageSize: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<NewsDomainModel> =
        repository.getArticle(source, page, pageSize, onStart, onComplete, onError)
            .map {
                it.toDomainModel()
            }
}