package com.kosmasfn.news.view.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.kosmasfn.domain.model.NewsDomainModel
import com.kosmasfn.news.R
import com.kosmasfn.news.databinding.ItemArticlesBinding
import com.kosmasfn.core.base.BaseBindingAdapter
import com.kosmasfn.core.base.BaseBindingViewHolder
import com.kosmasfn.utils.DateTimeHelper.convertDateFormat
import java.text.DateFormat
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

/**
 * Created by Kosmas on October 09, 2023.
 */
class HomeAdapter(
    private val onArticleClicked: ((NewsDomainModel.Article) -> Unit),
) : BaseBindingAdapter<BaseBindingViewHolder>() {

    private val items = mutableListOf<NewsDomainModel.Article>()

    @SuppressLint("NotifyDataSetChanged")
    fun addItems(list: List<NewsDomainModel.Article>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseBindingViewHolder {
        return BaseBindingViewHolder(
            ItemArticlesBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun updateBinding(holder: BaseBindingViewHolder, binding: ViewBinding, position: Int) {
        with(binding as ItemArticlesBinding) {
            items[position].urlToImage.apply {
                if (this.isNotEmpty()) {
                    Glide.with(ivImg.context).load(items[position].urlToImage).into(ivImg)
                }
            }
            tvTitle.text = items[position].title
            tvDescription.text = items[position].description
            tvDate.text = convertDateFormat(items[position].publishedAt) ?: ""

            btnFavorite.setFavoriteArticle(items[position].isFavorite)
            root.setOnClickListener {
                items[position].isFavoriteClicked = false
                onArticleClicked(items[position])
            }
            btnFavorite.setOnClickListener {
                items[position].isFavoriteClicked = true
                if (items[position].isFavorite) {
                    btnFavorite.setFavoriteArticle(false)
                    items[position].isFavorite = false
                } else {
                    btnFavorite.setFavoriteArticle(true)
                    items[position].isFavorite = true
                }
                onArticleClicked(items[position])
            }
        }
    }

    private fun ImageButton.setFavoriteArticle(isFavorite: Boolean) {
        setImageDrawable(
            ContextCompat.getDrawable(
                this.context, if (isFavorite) R.drawable.ic_love_filled else R.drawable.ic_love
            )
        )
    }

    override fun getItemCount(): Int = items.size
}