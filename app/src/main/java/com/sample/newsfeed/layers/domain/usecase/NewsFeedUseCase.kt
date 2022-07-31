package com.sample.newsfeed.layers.domain.usecase

import com.sample.core.layers.usecase.MainUseCase
import com.sample.news.BuildConfig
import com.sample.newsfeed.layers.data.repository.NewsRepository
import com.sample.newsfeed.layers.domain.model.ItemsResponse
import retrofit2.Response

class NewsFeedUseCase constructor(
    private val newsRepository: NewsRepository
) : MainUseCase<ItemsResponse, NewsFeedUseCase.Params>() {

    override suspend fun createUseCase(params: Params?): Response<ItemsResponse> {
        return newsRepository.getNyNewsFeedsList()
    }

    data class Params constructor(val apiKey: String = BuildConfig.API_KEY, val page: Int)
}
