package com.abdelmageed.flickersimages.data.common.module

import android.content.Context
import androidx.room.Room
import com.abdelmageed.flickersimages.data.locale_data_source.ImageDao
import com.abdelmageed.flickersimages.data.locale_data_source.ImagesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocaleDbModule {


    @Provides
    @Singleton
    fun provideImagesDatabase(@ApplicationContext context: Context): ImagesDatabase {
        return Room.databaseBuilder(
            context,
            ImagesDatabase::class.java,
            ImagesDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideDao(imageDb: ImagesDatabase): ImageDao {
        return imageDb.imageDao()
    }
}