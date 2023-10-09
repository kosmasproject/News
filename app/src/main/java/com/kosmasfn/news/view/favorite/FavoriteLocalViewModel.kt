package com.kosmasfn.news.view.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kosmasfn.domain.model.NewsDomainModel
import com.kosmasfn.domain.usecase.NewsLocalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Kosmas on October 09, 2023.
 */
@HiltViewModel
class FavoriteLocalViewModel @Inject constructor(private val useCase: NewsLocalUseCase) : ViewModel() {

    var isLoading = MutableLiveData(false)
    var errorMessage = MutableLiveData<String>(null)
    val articles = MutableLiveData<List<NewsDomainModel.Article>>()

    fun fetchArticle() {
        viewModelScope.launch {
            useCase.getNews().apply {
                articles.postValue(this)
            }
        }
    }
}