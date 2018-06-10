package com.example.matthew.ticketmaster_mvvm.di.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.matthew.ticketmaster_mvvm.di.qualifier.ViewModelKey
import com.example.matthew.ticketmaster_mvvm.modules.di.ViewModelFactory
import com.example.matthew.ticketmaster_mvvm.modules.list.viewmodel.ListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Binds view model factory
 *
 * Created by Matthew on 10/06/2018.
 */
@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ListViewModel::class)
    abstract fun bindListViewModel(listViewModel: ListViewModel): ViewModel

}