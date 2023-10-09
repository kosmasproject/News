package com.kosmasfn.domain.usecase

import com.kosmasfn.data.localdb.NewsLocalRepository
import com.kosmasfn.domain.model.NewsDomainModel
import com.kosmasfn.domain.toDomainModel
import com.kosmasfn.domain.toEntity
import javax.inject.Inject

/**
 * Created by Kosmas on October 09, 2023.
 */
class NewsLocalUseCaseImpl @Inject constructor(
    private val repository: NewsLocalRepository
) : NewsLocalUseCase {

    override suspend fun saveNews(data: NewsDomainModel.Article): Boolean {
        repository.saveNews(data.toEntity())
        return true
    }

    override fun getNews(): List<NewsDomainModel.Article> =
        repository.getNews().map { it.toDomainModel() }

    override fun deleteNews(url: String): Boolean = repository.deleteNews(url)

    override fun findArticleOnLocal(url: String): Boolean {
        return repository.findArticleOnLocal(url).isNotEmpty()
    }
}