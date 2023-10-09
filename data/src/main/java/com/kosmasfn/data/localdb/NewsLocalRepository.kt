package com.kosmasfn.data.localdb

/**
 * Created by Kosmas on October 09, 2023.
 */
interface NewsLocalRepository {
    suspend fun saveNews(data: NewsEntity): Boolean
    fun getNews(): Array<NewsEntity>
    fun deleteNews(url: String): Boolean
    fun findArticleOnLocal(url: String): List<NewsEntity>
}