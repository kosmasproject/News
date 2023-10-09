package com.kosmasfn.news.view.favorite

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.kosmasfn.core.base.BaseBindingAdapter
import com.kosmasfn.core.base.BaseBindingViewHolder
import com.kosmasfn.domain.model.NewsDomainModel
import com.kosmasfn.news.databinding.ItemFavoriteBinding
import com.kosmasfn.utils.DateTimeHelper

/**
 * Created by Kosmas on October 09, 2023.
 */
class FavoriteAdapter(
    private val onArticleClicked: ((NewsDomainModel.Article) -> Unit),
) : BaseBindingAdapter<BaseBindingViewHolder>() {

    private val items = mutableListOf<NewsDomainModel.Article>()

    @SuppressLint("NotifyDataSetChanged")
    fun addItems(list: List<NewsDomainModel.Article>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): com.kosmasfn.core.base.BaseBindingViewHolder {
        return BaseBindingViewHolder(
            ItemFavoriteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun updateBinding(holder: BaseBindingViewHolder, binding: ViewBinding, position: Int) {
        with(binding as ItemFavoriteBinding) {
            items[position].urlToImage.apply {
                if (this.isNotEmpty()) {
                    Glide.with(ivImg.context).load(items[position].urlToImage).into(ivImg)
                }
            }
            tvTitle.text = items[position].title
            tvDescription.text = items[position].description
            tvDate.text = DateTimeHelper.convertDateFormat(items[position].publishedAt) ?: ""
            root.setOnClickListener {
                items[position].isFavorite = false
                onArticleClicked(items[position])
            }
        }
    }

    override fun getItemCount(): Int = items.size
}