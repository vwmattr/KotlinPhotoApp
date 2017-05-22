package com.mattrein.photoapp

import dagger.android.DaggerApplication
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

/**
 * Unit tests for [MainActivity].
 */
@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(22))
class PhotoApplicationTest {

    lateinit var application : PhotoApplication

    @Before
    fun setUp() {
        application = RuntimeEnvironment.application as PhotoApplication
    }

    @Test
    fun creation() {
        assertThat(application).isInstanceOf(DaggerApplication::class.java)
    }

}
