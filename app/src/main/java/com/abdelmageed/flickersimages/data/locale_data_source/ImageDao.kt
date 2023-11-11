package com.abdelmageed.flickersimages.data.locale_data_source

import androidx.room.*
import com.abdelmageed.flickersimages.data.module.remote.dto.PhotoItem
import com.abdelmageed.flickersimages.domain.model.ImageModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageDao {
    @Query("SELECT * FROM images")
    fun getImages(): Flow<ImageModel>

    @Query("SELECT * FROM images WHERE imageId = :id")
    suspend fun getImageById(id: Int): ImageModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImage(images: ImageModel)

}