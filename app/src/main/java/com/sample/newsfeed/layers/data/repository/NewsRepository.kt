package com.sample.newsfeed.layers.data.repository

import com.sample.newsfeed.layers.domain.model.ItemsResponse
import retrofit2.Response

interface NewsRepository {

    suspend fun getNyNewsFeedsList(): Response<ItemsResponse>

}
