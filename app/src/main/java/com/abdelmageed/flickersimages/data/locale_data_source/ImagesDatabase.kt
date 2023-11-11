package com.abdelmageed.flickersimages.data.locale_data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.abdelmageed.flickersimages.domain.model.ImageModel
import com.abdelmageed.flickersimages.utils.ArrayListConverter

@Database(entities = [ImageModel::class], version = 1)
@TypeConverters(ArrayListConverter::class)
abstract class ImagesDatabase : RoomDatabase() {
    abstract fun imageDao(): ImageDao

    companion object {
        const val DATABASE_NAME = "images_db"
    }

}