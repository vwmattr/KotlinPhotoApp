package com.mattrein.photoapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.mattrein.photoapp.models.Photo

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val imageView = findViewById(R.id.imageview) as ImageView
        val photo = intent.getSerializableExtra(PHOTO) as Photo?

        //TODO: Test this!
        photo?.webformatURL.let {
            Glide.with(this)
                    .load(photo?.webformatURL)
                    .into(imageView)
        }

        imageView.setOnClickListener {
            finish()
        }
    }

    //This is how you add Constants in Kotlin
    companion object {
        val PHOTO = "PHOTO"
    }
}
