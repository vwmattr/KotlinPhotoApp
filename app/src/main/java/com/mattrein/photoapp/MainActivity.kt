package com.mattrein.photoapp

import android.content.Intent
import android.os.Bundle
import android.support.annotation.VisibleForTesting
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.mattrein.photoapp.api.PhotoRetriever
import com.mattrein.photoapp.models.Photo
import com.mattrein.photoapp.models.PhotoList
import dagger.android.AndroidInjection
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * The landing page for the app.
 * Note: this is open for testing purposes
 */
open class MainActivity : AppCompatActivity(), View.OnClickListener {

    @Inject
    lateinit var retriever : PhotoRetriever

    var photos : List<Photo>? = null
    var mainAdapter : MainAdapter? = null
    lateinit var recyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        injectActivity()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        recyclerView = findViewById(R.id.recyclerview) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        loadAndDisplayPhotos(getPhotoApiCallback())
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onClick(view: View?) {
        val intent = Intent(this, DetailActivity::class.java)
        var holder = view?.tag as PhotoViewHolder
        intent.putExtra(DetailActivity.PHOTO, mainAdapter?.getPhoto(holder.adapterPosition))
        startActivity(intent)
    }

    @VisibleForTesting
    fun loadAndDisplayPhotos(callback: Callback<PhotoList>) {
        retriever.getPhotos(callback)
    }

    @VisibleForTesting
    fun getPhotoApiCallback() : Callback<PhotoList> {
        return object : Callback<PhotoList> {
            override fun onFailure(call: Call<PhotoList>?, t: Throwable?) {
                Log.e("MainActivity", "Problem calling API", t)
            }

            override fun onResponse(call: Call<PhotoList>?, response: Response<PhotoList>?) {
                response?.isSuccessful.let {
                    this@MainActivity.photos = response?.body()?.hits
                    mainAdapter = MainAdapter(this@MainActivity.photos!!, this@MainActivity)
                    recyclerView.adapter = mainAdapter
                }
            }
        }
    }

    @VisibleForTesting
    fun injectActivity() {
        AndroidInjection.inject(this)
    }

}
