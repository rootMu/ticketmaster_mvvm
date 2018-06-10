package com.example.matthew.ticketmaster_mvvm.di.qualifier

import android.arch.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * Created by Matthew on 10/06/2018.
 */

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@MapKey
@Retention(AnnotationRetention.RUNTIME)
annotation class ViewModelKey(val value: KClass<out ViewModel>)