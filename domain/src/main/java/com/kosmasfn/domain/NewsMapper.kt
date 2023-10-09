package com.kosmasfn.domain

import com.kosmasfn.data.localdb.NewsEntity
import com.kosmasfn.data.model.NewsDataModel
import com.kosmasfn.domain.model.NewsDomainModel

/**
 * Created by Kosmas on October 09, 2023.
 */
fun NewsDataModel?.toDomainModel(): NewsDomainModel {
    return NewsDomainModel(
        this?.status ?: "",
        this?.totalResults ?: 0,
        this?.source?.map { it.toDomainModel() } ?: mutableListOf(),
        this?.articles?.map { it.toDomainModel() } ?: mutableListOf(),
    )
}

fun NewsDataModel.Source?.toDomainModel(): NewsDomainModel.Source {
    return NewsDomainModel.Source(
        this?.id ?: "",
        this?.name ?: "",
        this?.category ?: "",
        this?.description ?: "",
        this?.url ?: ""
    )
}

fun NewsDataModel.Article?.toDomainModel(): NewsDomainModel.Article {
    return NewsDomainModel.Article(
        this?.source.toDomainModel(),
        this?.author ?: "",
        this?.title ?: "",
        this?.description ?: "",
        this?.url ?: "",
        this?.urlToImage ?: "",
        this?.publishedAt ?: "",
        this?.content ?: "",
        isFavorite = false,
        isFavoriteClicked = false
    )
}

fun NewsEntity?.toDomainModel(): NewsDomainModel.Article {
    return NewsDomainModel.Article(
        author = this?.author ?: "",
        title = this?.title ?: "",
        description = this?.description ?: "",
        url = this?.url ?: "",
        urlToImage = this?.urlToImage ?: "",
        publishedAt = this?.publishedAt ?: "",
        content = this?.content ?: ""
    )
}

fun NewsDomainModel.Article?.toEntity(): NewsEntity {
    return NewsEntity(
        author = this?.author ?: "",
        title = this?.title ?: "",
        description = this?.description ?: "",
        url = this?.url ?: "",
        urlToImage = this?.urlToImage ?: "",
        publishedAt = this?.publishedAt ?: "",
        content = this?.content ?: ""
    )
}
