package com.mattrein.photoapp.api

import com.mattrein.photoapp.models.PhotoList
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Retriever for getting data via the PhotoAPI interface.
 */
class PhotoRetriever {

    private val service: PhotoApi

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://pixabay.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        service = retrofit.create(PhotoApi::class.java)
    }

    fun getPhotos(callback : Callback<PhotoList>) {
        val call = service.getPhotos()
        call.enqueue(callback)
    }

}