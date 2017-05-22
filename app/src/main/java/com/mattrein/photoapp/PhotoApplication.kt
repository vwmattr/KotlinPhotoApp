package com.mattrein.photoapp

import android.app.Application
import com.mattrein.photoapp.dagger.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

/**
 * [Application] class for the PhotoApp.
 */
class PhotoApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder().create(this)
    }

}
