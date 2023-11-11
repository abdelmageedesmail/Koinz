package com.abdelmageed.flickersimages.domain.images

import com.abdelmageed.flickersimages.data.common.utils.WrappedResponseWithError
import com.abdelmageed.flickersimages.data.module.remote.dto.BaseErrorResponse
import com.abdelmageed.flickersimages.data.module.remote.dto.FlickerImagesResponse
import com.abdelmageed.flickersimages.data.module.remote.dto.PhotoItem
import com.abdelmageed.flickersimages.domain.base.BaseResult
import com.abdelmageed.flickersimages.domain.model.ImageModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImagesUseCase @Inject constructor(private val imageRepository: ImageRepository) {

    suspend fun invoke(
        page: Int,
        perPage: Int
    ): Flow<BaseResult<FlickerImagesResponse, WrappedResponseWithError<Unit, BaseErrorResponse>>> {
        return imageRepository.getImages(page, perPage)
    }

    suspend fun invokeToDb(images: ImageModel) {
        imageRepository.insertImages(images)
    }

    fun invokeGetImagesFromDb(): Flow<ImageModel> {
        return imageRepository.getImages()
    }

}