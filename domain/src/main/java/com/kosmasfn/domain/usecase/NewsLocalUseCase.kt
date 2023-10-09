package com.kosmasfn.domain.usecase

import com.kosmasfn.domain.model.NewsDomainModel

/**
 * Created by Kosmas on October 09, 2023.
 */
interface NewsLocalUseCase {
    suspend fun saveNews(data: NewsDomainModel.Article): Boolean
    fun getNews(): List<NewsDomainModel.Article>
    fun deleteNews(url: String): Boolean
    fun findArticleOnLocal(url: String): Boolean
}