package com.sample.newsfeed.layers.data.api

import com.sample.news.BuildConfig
import com.sample.newsfeed.layers.domain.model.ItemsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsServiceApi {

    @GET("svc/mostpopular/v2/mostviewed/all-sections/7.json")
    suspend fun fetchAllSectionFeeds(
        @Query("api-key") apiKey: String = BuildConfig.API_KEY
    ): Response<ItemsResponse>

}
