package com.abdelmageed.flickersimages.data.common.utils

import com.google.gson.annotations.SerializedName

@androidx.annotation.Keep
data class ResponseWrapper<T>(
    @SerializedName("stat") var status: Boolean,
    @SerializedName("photos") var data: T? = null,
    @SerializedName("code") var code: Int? = null,
    var totalCount: Int? = null,
)

@androidx.annotation.Keep
data class WrappedResponseWithError<T, U>(
    var code: Int,
    @SerializedName("message") var message: String?=null,
    @SerializedName("stat") var status: Boolean,
    @SerializedName("code") var errors: U? = null,
    @SerializedName("photo") var data: T? = null,
)