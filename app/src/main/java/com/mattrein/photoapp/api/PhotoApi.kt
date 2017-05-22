package com.mattrein.photoapp.api

import com.mattrein.photoapp.models.PhotoList
import retrofit2.Call
import retrofit2.http.GET

/**
 * Retrofit API interface for accessing the Pixabay photo API.
 */
interface PhotoApi {

    @GET("?key=5426113-e2ae9f71b7307781f7c025c61&q=nature&image_type=photo")
    fun getPhotos() : Call<PhotoList>

}