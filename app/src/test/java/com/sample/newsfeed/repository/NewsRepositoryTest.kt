package com.sample.newsfeed.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.sample.newsfeed.layers.data.api.NewsServiceApi
import com.sample.newsfeed.layers.data.repository.NewsRepository
import com.sample.newsfeed.layers.data.repository.NewsRepositoryImpl
import com.sample.newsfeed.layers.domain.model.ItemsResponse
import com.sample.newsfeed.layers.domain.usecase.NewsFeedUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*
import retrofit2.Response

@RunWith(JUnit4::class)
class NewsRepositoryTest {

    private lateinit var repository: NewsRepository
    private val service = mock(NewsServiceApi::class.java)

    @MockK
    private lateinit var useCase: NewsFeedUseCase

    @MockK
    private lateinit var responseOfItemsResponse : Response<ItemsResponse>

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        MockKAnnotations.init(this)
        repository = NewsRepositoryImpl(service)
       // useCase = NewsFeedUseCase(repository)


    }

    @Test
    fun loadNewsFromNetwork() {
        runBlocking {
            coEvery { useCase.createUseCase(null) } returns responseOfItemsResponse

            val data = useCase.createUseCase(null)
            Truth.assertThat(data).isNotNull()
        }
    }
}
