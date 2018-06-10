package com.example.matthew.ticketmaster_mvvm.di.modules

import com.example.matthew.ticketmaster_mvvm.modules.list.ui.ListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Matthew on 10/06/2018.
 */
@Module
abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract fun bindListActivity(): ListActivity

}