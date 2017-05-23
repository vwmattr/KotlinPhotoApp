package com.mattrein.photoapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.mattrein.photoapp.models.Photo

/**
 * RecycleviewAdapter for displaying the list of photos in the MainActivity.
 * Note: Open for testing
 */
open class MainAdapter(var photos : List<Photo>,
                  var clickListener : View.OnClickListener) :
        RecyclerView.Adapter<PhotoViewHolder>() {

    override fun getItemCount(): Int {
        return photos.size
    }

    override fun onBindViewHolder(holder: PhotoViewHolder?, position: Int) {
        val photo = photos[position]
        holder?.tags?.text = photo.tags
        holder?.likes?.text = photo.likes.toString()
        holder?.favorites?.text = photo.favorites.toString()
        if (photo.previewURL.isNotEmpty()) {
            Glide.with(holder?.tags?.context)
                    .load(photo.previewURL)
                    .into(holder?.photo_item)
        }
    }

    //Note: Open for testing
    open fun getPhoto(adapterPosition: Int) : Photo {
        return photos[adapterPosition]
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PhotoViewHolder {
        val itemView = LayoutInflater.from(parent?.context)
                .inflate(R.layout.photo_item, parent, false)
        return PhotoViewHolder(itemView, clickListener)
    }

}