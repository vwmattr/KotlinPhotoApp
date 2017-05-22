package com.mattrein.photoapp.dagger.component

import com.mattrein.photoapp.PhotoApplication
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

/**
 * Top level component for PhotoApp dependencies.
 */
@Singleton
@Component(modules = arrayOf(AndroidInjectionModule::class))
interface ApplicationComponent : AndroidInjector<PhotoApplication> {
    
    /**
     * Component.Builder for this AndroidInjector.
     */
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<PhotoApplication>() {
        //Required for DaggerApplication injection
    }

}
