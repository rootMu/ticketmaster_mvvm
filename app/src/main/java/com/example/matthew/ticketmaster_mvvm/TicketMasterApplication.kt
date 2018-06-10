package com.example.matthew.ticketmaster_mvvm

import com.example.matthew.ticketmaster_mvvm.di.components.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

/**
 * Created by Matthew on 10/06/2018.
 */
class TicketMasterApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

}