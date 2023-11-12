package com.abdelmageed.flickersimages.data.common.module

import com.abdelmageed.flickersimages.data.locale_data_source.ImageDao
import com.abdelmageed.flickersimages.data.module.remote.api.ApiService
import com.abdelmageed.flickersimages.data.repository.ImageRepositoryImpl
import com.abdelmageed.flickersimages.domain.images.ImageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    init {
        System.loadLibrary("native-lib")
    }

    private external fun getBaseUrl(): String


    @Singleton
    @Provides
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient.Builder().apply {

            connectTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
            writeTimeout(60, TimeUnit.SECONDS)

            addInterceptor(provideHttpLoggingInterceptor())
        }.build()
    }


    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(getBaseUrl())
            .client(okHttpClient)
            .build()
    }


    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideImageRepo(apiService: ApiService, db: ImageDao): ImageRepository {
        return ImageRepositoryImpl(apiService, db)
    }

}