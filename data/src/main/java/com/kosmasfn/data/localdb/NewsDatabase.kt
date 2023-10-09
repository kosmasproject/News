package com.kosmasfn.data.localdb

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Created by Kosmas on October 09, 2023.
 */
@Database(entities = [NewsEntity::class], version = 1,
    exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun resultDao(): NewsDao
}