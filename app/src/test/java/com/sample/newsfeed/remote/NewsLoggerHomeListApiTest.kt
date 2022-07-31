package com.sample.newsfeed.remote


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertWithMessage
import com.sample.core.supporter.GsonProvider
import com.sample.newsfeed.MockResponseFileReader
import com.sample.newsfeed.layers.data.api.NewsServiceApi
import com.sample.newsfeed.layers.domain.model.ItemsResponse
import com.sample.util.CoroutineTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.Okio
import org.hamcrest.CoreMatchers
import org.junit.*
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

@ExperimentalCoroutinesApi
class NewsLoggerHomeListApiTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private var mockWebServer = MockWebServer()
    private lateinit var newsService: NewsServiceApi

    @Before
    fun setUp() {
        mockWebServer.start()
        newsService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(
                GsonConverterFactory.create(GsonProvider().instance)
            ).build()
            .create(NewsServiceApi::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `fetch news response is successful`() {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(MockResponseFileReader("articlesearch.json").content)

        mockWebServer.enqueue(response)
        runBlocking {
            val articleListResponse: Response<ItemsResponse> = newsService.fetchAllSectionFeeds()
            Truth.assertThat(articleListResponse.isSuccessful).isTrue()
        }
    }

    @Test
    fun `fetch articles response body has desired num_results`() {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(MockResponseFileReader("articlesearch.json").content)

        mockWebServer.enqueue(response)
        runBlocking {
            val articleListResponse: Response<ItemsResponse> = newsService.fetchAllSectionFeeds()
            assertWithMessage("Without me, it's just awesome")
                .that(articleListResponse.body()?.results?.size).isEqualTo(20)
        }
    }

    @Test
    fun requestNews() {
        runBlocking {
            enqueueResponse("articlesearch.json")
            val resultResponse = newsService.fetchAllSectionFeeds().body()?.results

            val request = mockWebServer.takeRequest()
            Assert.assertNotNull(resultResponse)

            assertWithMessage("There is an error").that(request.path).contains("/svc/mostpopular/v2/mostviewed/all-sections/7.json");

            //Assert.assertThat(request.path, CoreMatchers.`is`("/svc/search/v2/articlesearch.json"))
        }
    }

    @Test
    fun getNewsResponse() {
        runBlocking {
            enqueueResponse("articlesearch.json")
            val resultResponse = newsService.fetchAllSectionFeeds().body()
            val newsList = resultResponse?.results

            Assert.assertThat(resultResponse?.results?.size, CoreMatchers.`is`(20))
            Assert.assertThat(newsList?.size, CoreMatchers.`is`(20))
        }
    }

    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
        val inputStream = javaClass.classLoader
            ?.getResourceAsStream("$fileName")
        val source = Okio.buffer(Okio.source(inputStream))
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(
            mockResponse.setBody(
                source.readString(Charsets.UTF_8)
            )
        )
    }
}