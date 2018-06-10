package com.example.matthew.ticketmaster_mvvm.di.components

import android.app.Application
import com.example.matthew.ticketmaster_mvvm.di.modules.BuildersModule
import com.example.matthew.ticketmaster_mvvm.di.modules.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

/**
 * Created by Matthew on 10/06/2018.
 */

@Singleton
@Component(modules = [
AndroidSupportInjectionModule::class,
BuildersModule::class,
ViewModelFactoryModule::class
])
interface AppComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}