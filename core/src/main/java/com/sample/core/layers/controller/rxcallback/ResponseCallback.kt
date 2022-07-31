package com.sample.core.layers.controller.rxcallback

import com.sample.core.layers.entity.BaseError

interface OptimizedResponseCallBack<T>{
    fun onApiSuccess(response: T)
    fun onApiError(error: BaseError)
}
