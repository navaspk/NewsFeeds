package com.sample.newsfeed.di

import com.sample.core.supporter.GsonProvider
import com.sample.core.supporter.NetworkUtil
import com.sample.news.BuildConfig
import com.sample.newsfeed.layers.data.api.NewsServiceApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NetworkServiceModule {

    @Provides
    fun provideNetworkUtil(gsonProvider: GsonProvider) =
        NetworkUtil(gsonProvider, BuildConfig.BASE_URL)

    @Provides
    fun provideNetworkService(networkUtil: NetworkUtil) =
        networkUtil.create(NewsServiceApi::class.java)
}