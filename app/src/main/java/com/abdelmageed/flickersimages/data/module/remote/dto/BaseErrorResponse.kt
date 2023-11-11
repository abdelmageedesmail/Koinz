package com.abdelmageed.flickersimages.data.module.remote.dto

import com.google.gson.annotations.SerializedName

data class BaseErrorResponse(

	@field:SerializedName("stat")
	val stat: String? = null,

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("message")
	val message: String? = null
)


