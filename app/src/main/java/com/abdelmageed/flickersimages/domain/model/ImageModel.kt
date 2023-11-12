package com.abdelmageed.flickersimages.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.abdelmageed.flickersimages.data.module.remote.dto.PhotoItem
import com.abdelmageed.flickersimages.data.module.remote.dto.Photos
import com.abdelmageed.flickersimages.utils.ArrayListConverter

@Entity(tableName = "images")
data class ImageModel(
    @PrimaryKey(autoGenerate = true) var imageId: Int? = null,

    @ColumnInfo(name = "photoItems")
    var photos: List<PhotoItem?> = emptyList()
)