package com.mattrein.photoapp.api

import com.mattrein.photoapp.dagger.ActivityScope
import com.mattrein.photoapp.models.PhotoList
import retrofit2.Callback
import javax.inject.Inject

/**
 * Retriever for getting data via the PhotoAPI interface.
 * Note: Open for testing
 */
@ActivityScope
open class PhotoRetriever
@Inject constructor(private val service: PhotoApi) {

    //Note: Open for testing
    open fun getPhotos(callback: Callback<PhotoList>) {
        val call = service.getPhotos()
        call.enqueue(callback)
    }

}