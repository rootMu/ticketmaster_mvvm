package com.example.matthew.ticketmaster_mvvm.di.modules

import com.example.matthew.ticketmaster_mvvm.modules.list.ui.ListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * All Activities being injected into
 * @author Matthew Howells
 */
@Module
abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract fun bindListActivity(): ListActivity

}