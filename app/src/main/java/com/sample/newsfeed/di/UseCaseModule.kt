package com.sample.newsfeed.di

import com.sample.newsfeed.layers.data.repository.NewsRepository
import com.sample.newsfeed.layers.domain.usecase.NewsFeedUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideNewsFeedUseCase(repository: NewsRepository) = NewsFeedUseCase(repository)
}