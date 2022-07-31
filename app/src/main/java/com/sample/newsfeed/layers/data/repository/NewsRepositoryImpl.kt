package com.sample.newsfeed.layers.data.repository

import com.sample.news.BuildConfig
import com.sample.newsfeed.layers.data.api.NewsServiceApi
import com.sample.newsfeed.layers.domain.model.ItemsResponse
import retrofit2.Response
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val apiService: NewsServiceApi
) : NewsRepository {

   override suspend fun getNyNewsFeedsList(): Response<ItemsResponse> {
       return apiService.fetchAllSectionFeeds(BuildConfig.API_KEY)
   }
}
