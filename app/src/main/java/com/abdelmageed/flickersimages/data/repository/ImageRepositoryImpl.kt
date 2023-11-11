package com.abdelmageed.flickersimages.data.repository

import com.abdelmageed.flickersimages.data.common.utils.WrappedResponseWithError
import com.abdelmageed.flickersimages.data.locale_data_source.ImageDao
import com.abdelmageed.flickersimages.data.module.remote.api.ApiService
import com.abdelmageed.flickersimages.data.module.remote.dto.BaseErrorResponse
import com.abdelmageed.flickersimages.data.module.remote.dto.FlickerImagesResponse
import com.abdelmageed.flickersimages.data.module.remote.dto.PhotoItem
import com.abdelmageed.flickersimages.domain.base.BaseResult
import com.abdelmageed.flickersimages.domain.images.ImageRepository
import com.abdelmageed.flickersimages.domain.model.ImageModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val imageDao: ImageDao
) :
    ImageRepository {

    init {
        System.loadLibrary("native-lib")
    }

    private external fun getApiKey(): String

    override suspend fun getImages(
        page: Int,
        perPage: Int
    ): Flow<BaseResult<FlickerImagesResponse, WrappedResponseWithError<Unit, BaseErrorResponse>>> {
        return flow {

            val response = apiService.getImages(page, perPage, getApiKey())

            if (response.isSuccessful) {
                if (response.body()?.stat == "fail") {
                    val err: WrappedResponseWithError<Unit, BaseErrorResponse> =
                        WrappedResponseWithError(
                            response.code(),
                            response.body()!!.message,
                            false
                        )
                    emit(BaseResult.Error(err))
                } else {
                    response.body()?.let { emit(BaseResult.Success(it)) }
                }

            } else {
                val err: WrappedResponseWithError<Unit, BaseErrorResponse> =
                    WrappedResponseWithError(
                        response.code(),
                        response.message(),
                        response.isSuccessful
                    )
                emit(BaseResult.Error(err))
            }
        }
    }

    override fun getImages(): Flow<ImageModel> {
        return imageDao.getImages()
    }

    override suspend fun getImageById(id: Int): ImageModel? {
        return imageDao.getImageById(id)
    }

    override suspend fun insertImages(images: ImageModel) {
        imageDao.insertImage(images)
    }


}