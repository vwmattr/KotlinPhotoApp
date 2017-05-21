package com.mattrein.photoapp.models

/**
 * Maps to the entity that indicates a top level list of Photos parsed from the Pixabay API.
 */
data class PhotoList(val hits : List<Photo>) {
}