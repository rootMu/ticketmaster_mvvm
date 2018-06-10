package com.example.matthew.ticketmaster_mvvm.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * Created by Matthew on 10/06/2018.
 */

@Module
class AndroidModule {

    /**
     * Provide android context
     */
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext
}