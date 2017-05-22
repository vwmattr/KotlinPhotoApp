package com.mattrein.photoapp.dagger.module

import com.mattrein.photoapp.api.PhotoApi

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Dagger2 Module that provides dependencies for interacting with APIs.
 */
@Module
class ApiModule {

    @Provides
    @Singleton
    fun providePhotoApi(): PhotoApi {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://pixabay.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit.create(PhotoApi::class.java)
    }

}
