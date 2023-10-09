package com.kosmasfn.data.di

import android.content.Context
import androidx.room.Room
import com.kosmasfn.data.localdb.NewsDao
import com.kosmasfn.data.localdb.NewsDatabase
import com.kosmasfn.data.localdb.NewsLocalRepository
import com.kosmasfn.data.localdb.NewsLocalRepositoryImpl
import com.kosmasfn.data.network.service.NewsApiService
import com.kosmasfn.data.repository.NewsRepository
import com.kosmasfn.data.repository.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

/**
 * Created by Kosmas on October 09, 2023
 */
@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Reusable
    @Provides
    fun provideNewsRepository(
        @Named(NetworkModule.AUTH_API_SERVICE) apiService: NewsApiService
    ): NewsRepository = NewsRepositoryImpl(apiService)

    @Reusable
    @Provides
    fun provideNewsDatabase(@ApplicationContext context: Context): NewsDatabase {
        try {
            return Room.databaseBuilder(context, NewsDatabase::class.java, "news_db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        } catch (e: Exception) {
            throw e
        }
    }

    @Reusable
    @Provides
    fun provideResultDao(database: NewsDatabase): NewsDao {
        return database.resultDao()
    }

    @Provides
    @Reusable
    fun provideResultRepository(newsDao: NewsDao): NewsLocalRepository =
        NewsLocalRepositoryImpl(newsDao)

}