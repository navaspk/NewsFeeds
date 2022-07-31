package com.sample.newsfeed.di

import com.sample.newsfeed.layers.data.repository.NewsRepository
import com.sample.newsfeed.layers.data.repository.NewsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(repo: NewsRepositoryImpl): NewsRepository
}
