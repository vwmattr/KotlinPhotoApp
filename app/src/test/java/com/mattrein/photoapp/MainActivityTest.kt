package com.mattrein.photoapp

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.mattrein.photoapp.api.PhotoRetriever
import com.mattrein.photoapp.models.Photo
import com.mattrein.photoapp.models.PhotoList
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.util.ActivityController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Unit tests for [MainActivity].
 */
@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(22))
class MainActivityTest {

    lateinit var activity : MainActivity
    lateinit var activityController : ActivityController<MainActivity>

    @Before
    fun setUp() {
        activityController = Robolectric.buildActivity(MainActivity::class.java)
        activity = activityController.get()
    }

    @Test
    fun creation() {
        assertThat(activity).isInstanceOf(AppCompatActivity::class.java)
    }

    @Test
    fun loadAndDisplayPhotos() {
        //1) Arrange
        val mockRetriever : PhotoRetriever = mock()
        activity.retriever = mockRetriever
        val mockCallback : Callback<PhotoList> = mock()

        //2) Act
        activity.loadAndDisplayPhotos(mockCallback)

        //3) Assert
        verify(mockRetriever).getPhotos(mockCallback)
    }

    @Test
    fun photoApiCallback_onResponse() {
        //1) Arrange
        val mockRecyclerView : RecyclerView = RecyclerView(RuntimeEnvironment.application)
        activity.recyclerView = mockRecyclerView

        val callback = activity.getPhotoApiCallback()
        val mockCall : Call<PhotoList> = mock()

        val hitList : MutableList<Photo> = ArrayList()
        val mockPhoto : Photo = Photo("id", 1, 1, "tags", "preview", "hiRes")
        hitList.add(mockPhoto)
        val mockPhotoList : PhotoList = PhotoList(hitList)
        val mockResponse : Response<PhotoList> = Response.success(mockPhotoList)

        assertThat(mockRecyclerView.adapter).isNull()

        //2) Act
        callback?.onResponse(mockCall, mockResponse)

        //3) Assert
        assertThat(activity.photos).isEqualTo(hitList)
        assertThat(activity.mainAdapter).isNotNull()
        assertThat(mockRecyclerView.adapter).isEqualTo(activity.mainAdapter)
    }

    @Test
    fun onClick() {
        //1) Arrange
        val mockPhoto : Photo = Photo("id", 1, 1, "tags", "preview", "hiRes")
        val parent : FrameLayout = FrameLayout(RuntimeEnvironment.application)
        val view : View = LayoutInflater.from(RuntimeEnvironment.application)
                .inflate(R.layout.photo_item, parent, false)
        val holder : PhotoViewHolder = PhotoViewHolder(view, null)
        activityController.create()
        activity.mainAdapter = mock()
        whenever(activity.mainAdapter?.getPhoto(anyInt())).thenReturn(mockPhoto)

        //2) Act
        activity.onClick(view)

        //3) Assert
        verify(activity.mainAdapter)?.getPhoto(anyInt())

        val startedIntent = shadowOf(RuntimeEnvironment.application).peekNextStartedActivity()
        val shadowIntent = shadowOf(startedIntent)

        assertThat(shadowIntent.getIntentClass()).isEqualTo(DetailActivity::class.java)
        val selectedPhoto = startedIntent.getSerializableExtra(DetailActivity.PHOTO)
        assertThat(selectedPhoto).isEqualTo(mockPhoto)
    }

}
