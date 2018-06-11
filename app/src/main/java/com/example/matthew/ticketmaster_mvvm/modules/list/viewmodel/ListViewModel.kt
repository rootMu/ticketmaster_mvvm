package com.example.matthew.ticketmaster_mvvm.modules.list.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.matthew.ticketmaster_mvvm.BuildConfig
import com.example.matthew.ticketmaster_mvvm.model.event.Event
import com.example.matthew.ticketmaster_mvvm.network.TicketMasterApiService
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

/**
 * view model for [ListActivity]
 * will handle api calls using LiveData
 *
 * @author Matthew Howells
 */
class ListViewModel @Inject constructor( private val mApiService: TicketMasterApiService, private val mGson: Gson) : ViewModel() {

    companion object {
        val TAG : String = ListViewModel::class.java.simpleName.toString()
    }

    //temporarily a String to be updated later when data type is created
    val mEventLiveData = MutableLiveData<ArrayList<Event>>()

    private val mDisposable: CompositeDisposable = CompositeDisposable()

    /**
     * updates [mTicketLiveData] with [Ticket] retrieved from API call
     * TODO('Update this call to use data retrieved from call')
     */
    fun updateLiveData(events: ArrayList<Event>) {
        mEventLiveData.postValue(events)
    }

    /**
     * makes #unknownapicall api call
     */
    fun fetchEvents() {
        mApiService.getEvents(apiKey = BuildConfig.API_KEY, marketID = "202",size = 50)
                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                .subscribe({it.eventData?.events?.let {
                    updateLiveData(it)
                } }, { _ ->
                }) {
                }.addTo(mDisposable)
    }

    /**
     * favourites the given event
     */
    fun favouriteEvent(id: String) {
        Log.d(TAG,"id: $id")
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