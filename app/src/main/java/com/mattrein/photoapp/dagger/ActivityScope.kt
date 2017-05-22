package com.mattrein.photoapp.dagger

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Scope

/**
 * Annotation for Dagger2 components that signifies the scope of the component is the Activity to
 * that the Component injects.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
annotation class ActivityScope