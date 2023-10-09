package com.kosmasfn.data.localdb

import javax.inject.Inject

/**
 * Created by Kosmas on October 09, 2023.
 */
class NewsLocalRepositoryImpl @Inject constructor(
    private val newsDao: NewsDao
) : NewsLocalRepository {

    override suspend fun saveNews(data: NewsEntity): Boolean {
        newsDao.saveResult(data)
        return true
    }

    override fun getNews(): Array<NewsEntity> = newsDao.result

    override fun deleteNews(url: String): Boolean {
        newsDao.deleteItemNews(url)
        return true
    }

    override fun findArticleOnLocal(url: String): List<NewsEntity> {
        return newsDao.findArticleOnLocal(url)
    }
}