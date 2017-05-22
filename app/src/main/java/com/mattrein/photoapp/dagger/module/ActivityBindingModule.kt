package com.mattrein.photoapp.dagger.module

import com.mattrein.photoapp.MainActivity
import com.mattrein.photoapp.dagger.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Module for building the subcomponents for various Dagger-injected Activities in the app.
 */
@Module
abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun mainActivity() : MainActivity

}
