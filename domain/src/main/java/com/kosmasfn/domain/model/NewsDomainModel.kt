package com.kosmasfn.domain.model

/**
 * Created by Kosmas on October 09, 2023.
 */

data class NewsDomainModel(
    val status: String,
    val totalResults: Int,
    var source: List<Source> = mutableListOf(),
    val articles: List<Article> = mutableListOf()
) {

    data class Source(
        var id: String = "",
        var name: String = "",
        var category: String = "",
        var description: String = "",
        var url: String = ""
    )

    data class Article(
        var source: Source = Source(),
        var author: String = "",
        var title: String = "",
        var description: String = "",
        var url: String = "",
        var urlToImage: String = "",
        var publishedAt: String = "",
        var content: String = "",
        var isFavorite: Boolean = false,
        var isFavoriteClicked: Boolean = false,
    )
}


