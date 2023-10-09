package com.kosmasfn.news.view.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kosmasfn.domain.model.NewsDomainModel
import com.kosmasfn.domain.usecase.NewsLocalUseCase
import com.kosmasfn.domain.usecase.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Kosmas on October 09, 2023.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: NewsUseCase,
    private val newsLocalUseCase: NewsLocalUseCase
) : ViewModel() {

    var isLoading = MutableLiveData(false)
    var errorMessage = MutableLiveData<String>(null)
    val sources = MutableLiveData<NewsDomainModel>()
    val articles = MutableLiveData<NewsDomainModel>()

    fun fetchSource(category: String, page: Int) {
        viewModelScope.launch {
            useCase.getSourcesByCategory(
                category = category,
                page = page,
                onStart = { isLoading.postValue(true) },
                onComplete = { isLoading.postValue(false) },
                onError = { errorMessage.postValue(it) }
            ).collect {
                sources.postValue(it)
            }
        }
    }

    fun fetchSourceByQuery(source: String, page: Int) {
        viewModelScope.launch {
            useCase.getSources(
                sources = source,
                page = page,
                onStart = { isLoading.postValue(true) },
                onComplete = { isLoading.postValue(false) },
                onError = { errorMessage.postValue(it) }
            ).collect {
                if (it.source.isEmpty()) {
                    val sources = mutableListOf<NewsDomainModel.Source>()
                    it.articles.forEach { article ->
                        sources.add(article.source)
                    }
                    sources.distinct().apply {
                        it.source = this
                    }
                }
                sources.postValue(it)
            }
        }
    }

    fun fetchArticle(source: String, page: Int) {
        viewModelScope.launch {
            useCase.getArticle(
                source = source,
                page = page,
                onStart = { isLoading.postValue(true) },
                onComplete = { isLoading.postValue(false) },
                onError = { errorMessage.postValue(it) }
            ).collect {
                it.articles.forEach { article ->
                    newsLocalUseCase.findArticleOnLocal(article.url).apply {
                        article.isFavorite = this
                    }
                }
                articles.postValue(it)
            }
        }
    }

    fun saveNews(news: NewsDomainModel.Article) {
        viewModelScope.launch { newsLocalUseCase.saveNews(news) }
    }

    fun removeNewsFromLocal(url: String) {
        viewModelScope.launch { newsLocalUseCase.deleteNews(url) }
    }
}