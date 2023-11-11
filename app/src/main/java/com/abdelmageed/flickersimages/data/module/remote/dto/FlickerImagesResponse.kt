package com.abdelmageed.flickersimages.data.module.remote.dto

import com.google.gson.annotations.SerializedName

@androidx.annotation.Keep
data class FlickerImagesResponse(

    @field:SerializedName("stat")
    val stat: String? = null,
    @field:SerializedName("code")
    val code: Int? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("photos")
    val photos: Photos? = null
)

@androidx.annotation.Keep
data class Photos(

    @field:SerializedName("perpage")
    val perpage: Int? = null,

    @field:SerializedName("total")
    val total: Int? = null,

    @field:SerializedName("pages")
    val pages: Int? = null,

    @field:SerializedName("photo")
    val photo: MutableList<PhotoItem?>,

    @field:SerializedName("page")
    val page: Int? = null
)

@androidx.annotation.Keep
data class PhotoItem(

    @field:SerializedName("owner")
    val owner: String? = null,

    @field:SerializedName("server")
    val server: String? = null,

    @field:SerializedName("ispublic")
    val ispublic: Int? = null,

    @field:SerializedName("isfriend")
    val isfriend: Int? = null,

    @field:SerializedName("farm")
    val farm: Int? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("secret")
    val secret: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("isfamily")
    val isfamily: Int? = null
)
