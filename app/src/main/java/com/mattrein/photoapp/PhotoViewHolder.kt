package com.mattrein.photoapp

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

/**
 * ViewHolder that displays a photo in the MainAdapter
 */
class PhotoViewHolder(itemView : View, clickListener: View.OnClickListener?)
    : RecyclerView.ViewHolder(itemView) {
    var tags : TextView
    var likes : TextView
    var favorites : TextView
    var photo_item : ImageView

    init {
        //Set the click listener
        if (clickListener != null) {
            itemView.setOnClickListener(clickListener)
        }
        itemView.tag = this
        tags = itemView.findViewById(R.id.tags) as TextView
        likes = itemView.findViewById(R.id.likes) as TextView
        favorites = itemView.findViewById(R.id.favorites) as TextView
        photo_item = itemView.findViewById(R.id.photo_item) as ImageView
    }
}