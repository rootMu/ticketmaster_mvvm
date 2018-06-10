package com.example.matthew.ticketmaster_mvvm.modules.list.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by Matthew on 10/06/2018.
 */

/**
 * view model for [ListActivity]
 * will handle api calls using LiveData
 */
class ListViewModel @Inject constructor() : ViewModel() {

    //temporarily a String to be updated later when data type is created
    val mTicketLiveData = MutableLiveData<ArrayList<String>>()

    private val mDisposable: CompositeDisposable = CompositeDisposable()

    /**
     * updates [mTicketLiveData] with [Ticket] retrieved from API call
     * TODO('Update this call to use data retrieved from call')
     */
    fun updateLiveData() {
        mTicketLiveData.postValue(ArrayList())
    }

    /**
     * makes #unknownapicall api call
     */
    fun fetchEvents() {

        //Make API call to get events
    }

    /**
     * favourites the given event
     */
    fun favouriteEvent(event: String) {

        //saves to favourites list
    }

    /**
     * clears all disposables within CompositeDisposable [mDisposable]
     */
    override fun onCleared() {
        super.onCleared()
        mDisposable.clear()
    }
}