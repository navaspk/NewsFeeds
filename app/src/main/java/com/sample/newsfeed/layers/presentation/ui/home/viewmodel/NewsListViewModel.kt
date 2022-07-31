package com.sample.newsfeed.layers.presentation.ui.home.viewmodel

import androidx.lifecycle.viewModelScope
import com.comera.core.domain.rxcallback.OptimizedCallbackWrapper
import com.sample.core.layers.entity.BaseError
import com.sample.newsfeed.base.BaseNavigator
import com.sample.newsfeed.base.BaseViewModel
import com.sample.newsfeed.layers.domain.model.ItemsResponse
import com.sample.newsfeed.layers.domain.model.ResultsItem
import com.sample.newsfeed.layers.domain.usecase.NewsFeedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * VM class responsible for making call to domain to get the data from data source.
 * Same view model can be use for future to get different data from dat source as we implement using
 * intention
 */
@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val useCase: NewsFeedUseCase
) : BaseViewModel<BaseNavigator>() {

    // region VARIABLES

    val userIntent = Channel<NewsIntent>(Channel.UNLIMITED)
    private val _contentFLow = MutableStateFlow<ItemResult>(ItemResult.Loading)
    val contentFLow: StateFlow<ItemResult> get() = _contentFLow

    init {
        handleNewsIntent()
    }

    // endregion

    // region UTIL

    private fun handleNewsIntent() {
        viewModelScope.launch(Dispatchers.IO) {
            userIntent.consumeAsFlow().collect {
                when (it) {
                    is NewsIntent.GetSearchedNews ->
                        getPopularNews()
                }
            }
        }
    }

    private fun getPopularNews() {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.execute(
                callbackWrapper = Subscriber(),
                coroutineScope = viewModelScope,
                dispatcher = Dispatchers.IO
            )
        }
    }

    // endregion


    private inner class Subscriber :
        OptimizedCallbackWrapper<ItemsResponse>() {

        override fun onApiSuccess(response: ItemsResponse) {
            _contentFLow.value = ItemResult.Success(response.results)
        }

        override fun onApiError(error: BaseError) {
            _contentFLow.value = ItemResult.Error(error.message)
        }
    }

    /**
     * This class for handling result from data source
     */
    sealed class ItemResult {
        data class Success(val item: List<ResultsItem?>?) : ItemResult()
        data class Error(val message: String? = "Unknown error"): ItemResult()
        object Loading: ItemResult()
    }

    /**
     * This class for handling UI intention, mix of MVI
     */
    sealed class NewsIntent {
        object GetSearchedNews : NewsIntent()
    }
}
