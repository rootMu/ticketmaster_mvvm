package com.example.matthew.ticketmaster_mvvm.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * Android Specific Dependancies
 *
 * @author Matthew Howells
 */

@Module
class AndroidModule {

    /**
     * Provide android context
     */
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext
}