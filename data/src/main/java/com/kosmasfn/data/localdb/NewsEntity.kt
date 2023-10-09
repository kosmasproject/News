package com.kosmasfn.data.localdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kosmasfn.data.model.NewsDataModel

/**
 * Created by Kosmas on October 09, 2023.
 */
@Entity
data class NewsEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    @ColumnInfo(name = "author")
    val author: String? = null,

    @ColumnInfo(name = "title")
    val title: String? = null,

    @ColumnInfo(name = "description")
    val description: String? = null,

    @ColumnInfo(name = "url")
    val url: String? = null,

    @ColumnInfo(name = "urlToImage")
    val urlToImage: String? = null,

    @ColumnInfo(name = "publishedAt")
    val publishedAt: String? = null,

    @ColumnInfo(name = "content")
    val content: String? = null

) : ModelEntity<NewsDataModel.Article> {

    override fun mapToEntity(): NewsDataModel.Article {
        return NewsDataModel.Article(
            source = null,
            author = author,
            title = title,
            description = description,
            url = url,
            urlToImage = urlToImage,
            publishedAt = publishedAt,
            content = content
        )
    }
}