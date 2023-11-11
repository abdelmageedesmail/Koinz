package com.abdelmageed.flickersimages.domain.images

import com.abdelmageed.flickersimages.data.common.utils.ResponseWrapper
import com.abdelmageed.flickersimages.data.common.utils.WrappedResponseWithError
import com.abdelmageed.flickersimages.data.module.remote.dto.FlickerImagesResponse
import com.abdelmageed.flickersimages.data.module.remote.dto.BaseErrorResponse
import com.abdelmageed.flickersimages.domain.base.BaseResult

import kotlinx.coroutines.flow.Flow

interface ImageRepository {
    suspend fun getImages(
        page: Int,
        perPage: Int
    ): Flow<BaseResult<FlickerImagesResponse, WrappedResponseWithError<Unit, BaseErrorResponse>>>
}