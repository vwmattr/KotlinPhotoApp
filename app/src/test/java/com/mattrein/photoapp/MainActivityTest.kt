package com.mattrein.photoapp

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Unit tests for [MainActivity].
 */
@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(22))
class MainActivityTest {

    var activity : Activity? = null

    @Before
    fun setUp() {
        activity = Robolectric.buildActivity(MainActivity::class.java).get()
    }

    @Test
    fun creation() {
        assertThat(activity).isInstanceOf(AppCompatActivity::class.java)
    }

}
