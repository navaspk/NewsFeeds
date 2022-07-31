package com.sample.newsfeed.di

import com.sample.core.supporter.GsonProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CommonModule {

    @Provides
    fun provideGsonProvider() = GsonProvider()
}
