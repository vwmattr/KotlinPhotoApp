package com.mattrein.photoapp.dagger.component

import com.mattrein.photoapp.PhotoApplication
import com.mattrein.photoapp.api.PhotoApi
import com.mattrein.photoapp.dagger.module.ActivityBindingModule
import com.mattrein.photoapp.dagger.module.ApiModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

/**
 * Top level component for PhotoApp dependencies.
 */
@Singleton
@Component(modules = arrayOf(
        AndroidInjectionModule::class,
        ApiModule::class,
        ActivityBindingModule::class))
interface ApplicationComponent : AndroidInjector<PhotoApplication> {

    /**
     * Component.Builder for this AndroidInjector.
     */
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<PhotoApplication>() {
        //Required for DaggerApplication injection
        abstract fun apiModule(apiModule: ApiModule): Builder

        override fun seedInstance(p0: PhotoApplication?) {
            apiModule(ApiModule())
        }

        abstract override fun build(): ApplicationComponent
    }

    fun photoApi(): PhotoApi

}
