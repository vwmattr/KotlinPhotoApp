package com.mattrein.photoapp.api

import com.mattrein.photoapp.dagger.ActivityScope
import com.mattrein.photoapp.models.PhotoList
import retrofit2.Callback
import javax.inject.Inject

/**
 * Retriever for getting data via the PhotoAPI interface.
 */
@ActivityScope
class PhotoRetriever
@Inject constructor(private val service: PhotoApi) {

    fun getPhotos(callback: Callback<PhotoList>) {
        val call = service.getPhotos()
        call.enqueue(callback)
    }

}