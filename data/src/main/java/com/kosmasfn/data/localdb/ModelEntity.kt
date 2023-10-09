package com.kosmasfn.data.localdb

/**
 * Created by Kosmas on October 09, 2023.
 */
interface ModelEntity<T> {
    fun mapToEntity(): T
}

fun <T> Array<ModelEntity<T>>.mapToEntityList(): MutableList<T> {
    val list = mutableListOf<T>()
    this.forEach {
        list.add(it.mapToEntity())
    }
    return list.toMutableList()
}