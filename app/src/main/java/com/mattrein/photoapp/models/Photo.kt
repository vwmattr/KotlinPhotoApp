package com.mattrein.photoapp.models

import java.io.Serializable

/**
 * Maps to the Photo entity that we parse from the Pixabay API.
 */
data class Photo(val id : String,
                 val likes : Int,
                 val favorites : Int,
                 val tags : String,
                 val previewURL : String,
                 val webformatURL : String) : Serializable {
}