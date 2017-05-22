package com.mattrein.photoapp

import android.view.View
import android.widget.FrameLayout
import com.mattrein.photoapp.models.Photo
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import java.util.*

/**
 * Unit tests for [MainAdapter].
 */
@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(22))
class MainAdapterTest {

    var adapter : MainAdapter? = null
    val photos : List<Photo>
    val photo1 : Photo
    val photo2 : Photo
    val photo3 : Photo
    val onClickListener : View.OnClickListener = mock(View.OnClickListener::class.java)

    init {
        photo1 = Photo("photo1", 1, 1, "photo1tags", "photo1Url", "photo1WebUrl")
        photo2 = Photo("photo2", 2, 2, "photo2tags", "photo2Url", "photo2WebUrl")
        photo3 = Photo("photo3", 3, 3, "photo3tags", "photo3Url", "photo3WebUrl")
        photos = ArrayList(Arrays.asList(photo1, photo2, photo3))
    }

    @Before
    fun setUp() {
        adapter = MainAdapter(photos, onClickListener)
    }

    @Test
    fun getItemCount() {
        assertThat(adapter?.itemCount).isEqualTo(photos.size)
    }

    @Test
    fun getPhoto() {
        assertThat(adapter?.getPhoto(0)).isEqualTo(photo1)
        assertThat(adapter?.getPhoto(1)).isEqualTo(photo2)
        assertThat(adapter?.getPhoto(2)).isEqualTo(photo3)
    }

    @Test
    fun onCreateViewHolder() {
        //1) Arrange
        val parentView = FrameLayout(RuntimeEnvironment.application)

        //2) Act
        val viewHolder = adapter?.onCreateViewHolder(parentView, 0)

        //3) Assert
        assertThat(viewHolder).isInstanceOf(MainAdapter.PhotoViewHolder::class.java)
    }

    @Test
    fun onBindViewHolder_item0() {
        //1) Arrange
        val parentView = FrameLayout(RuntimeEnvironment.application)
        val viewHolder = adapter?.onCreateViewHolder(parentView, 0)

        //2) Act
        adapter?.onBindViewHolder(viewHolder, 0)

        //3) Assert
        assertThat(viewHolder?.tags?.text).isEqualTo(photo1.tags)
        assertThat(viewHolder?.likes?.text).isEqualTo(photo1.likes.toString())
        assertThat(viewHolder?.favorites?.text).isEqualTo(photo1.favorites.toString())
    }

    @Test
    fun onBindViewHolder_item1() {
        //1) Arrange
        val parentView = FrameLayout(RuntimeEnvironment.application)
        val viewHolder = adapter?.onCreateViewHolder(parentView, 0)

        //2) Act
        adapter?.onBindViewHolder(viewHolder, 1)

        //3) Assert
        assertThat(viewHolder?.tags?.text).isEqualTo(photo2.tags)
        assertThat(viewHolder?.likes?.text).isEqualTo(photo2.likes.toString())
        assertThat(viewHolder?.favorites?.text).isEqualTo(photo2.favorites.toString())
    }

}
