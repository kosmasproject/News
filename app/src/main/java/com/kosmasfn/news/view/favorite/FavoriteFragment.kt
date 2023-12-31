package com.kosmasfn.news.view.favorite

import android.view.LayoutInflater
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.kosmasfn.domain.model.NewsDomainModel
import com.kosmasfn.news.databinding.FragmentFavoriteBinding
import com.kosmasfn.news.view.WebViewActivity
import com.kosmasfn.core.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Kosmas on 09/10/23.
 */
@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>() {

    private val viewModel: FavoriteLocalViewModel by viewModels()

    override fun setBinding(layoutInflater: LayoutInflater) =
        FragmentFavoriteBinding.inflate(layoutInflater)

    override fun setUp() {
        initObserver()
        fetchArticles()
    }

    private fun initObserver() {
        viewModel.isLoading.observe(this) { showLoading(it) }
        viewModel.errorMessage.observe(this) {
            it?.let {
                showSnackBar(it, requireView())
                showLoading(false)
            }
        }
        viewModel.articles.observe(this) {
            initAdapter(it)
        }
    }

    private fun showLoading(show: Boolean) {
        getViewBinding().progressBar.isVisible = show
    }

    private fun initAdapter(data: List<NewsDomainModel.Article>) {
        with(getViewBinding().rvArticles) {
            adapter = FavoriteAdapter {
                WebViewActivity.launchIntent(requireActivity(), it.url)
            }.apply { addItems(data) }
        }
    }

    private fun fetchArticles() {
        viewModel.fetchArticle()
    }
}