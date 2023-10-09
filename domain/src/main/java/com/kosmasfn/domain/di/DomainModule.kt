package com.kosmasfn.domain.di

import com.kosmasfn.data.localdb.NewsLocalRepository
import com.kosmasfn.data.repository.NewsRepository
import com.kosmasfn.domain.usecase.NewsLocalUseCase
import com.kosmasfn.domain.usecase.NewsLocalUseCaseImpl
import com.kosmasfn.domain.usecase.NewsUseCase
import com.kosmasfn.domain.usecase.NewsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Kosmas on October 09, 2023.
 */
@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    @Reusable
    fun provideNewsUseCase(
        repository: NewsRepository
    ): NewsUseCase = NewsUseCaseImpl(repository)

    @Provides
    @Reusable
    fun provideNewsLocalUseCase(
        repository: NewsLocalRepository
    ): NewsLocalUseCase = NewsLocalUseCaseImpl(repository)

}