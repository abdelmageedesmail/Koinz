package com.abdelmageed.flickersimages.domain.base

import com.abdelmageed.flickersimages.data.module.remote.dto.FlickerImagesResponse

sealed class BaseResult<out T : Any, out U : Any> {
    @androidx.annotation.Keep
    data class Success<T : Any>(val data: FlickerImagesResponse?) : BaseResult<T, Nothing>()

//    @androidx.annotation.Keep
//    data class ErrorResponse<String : Any>(val message: kotlin.String?) : BaseResult<String, Nothing>()

    @androidx.annotation.Keep
    data class Error<U : Any>(val rawResponse: U) : BaseResult<Nothing, U>()
}