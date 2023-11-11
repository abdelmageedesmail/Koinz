package com.abdelmageed.flickersimages.data.module.remote.api

import com.abdelmageed.flickersimages.data.common.utils.ResponseWrapper
import com.abdelmageed.flickersimages.data.module.remote.dto.FlickerImagesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("?method=flickr.photos.search&format=json&nojsoncallback=50&text=Color")
    suspend fun getImages(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("api_key") apiKey: String
    ): Response<FlickerImagesResponse>

}