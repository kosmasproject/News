package com.kosmasfn.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Kosmas on October 09, 2023
 */
data class NewsDataModel(
    @SerializedName("status") @Expose val status: String? = null,
    @SerializedName("totalResults") @Expose val totalResults: Int? = null,
    @SerializedName("sources") @Expose val source: List<Source>? = null,
    @SerializedName("articles") @Expose val articles: List<Article>? = null
) {
    data class Source(
        @SerializedName("id") @Expose var id: String? = null,
        @SerializedName("name") @Expose var name: String? = null,
        @SerializedName("category") @Expose var category: String? = null,
        @SerializedName("description") @Expose var description: String? = null,
        @SerializedName("url") @Expose var url: String? = null
    )

    data class Article(
        @SerializedName("source") @Expose var source: Source? = null,
        @SerializedName("author") @Expose var author: String? = null,
        @SerializedName("title") @Expose var title: String? = null,
        @SerializedName("description") @Expose var description: String? = null,
        @SerializedName("url") @Expose var url: String? = null,
        @SerializedName("urlToImage") @Expose var urlToImage: String? = null,
        @SerializedName("publishedAt") @Expose var publishedAt: String? = null,
        @SerializedName("content") @Expose var content: String? = null
    )
}


