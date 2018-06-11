package com.example.matthew.ticketmaster_mvvm.modules.list.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.util.Log
import com.example.matthew.ticketmaster_mvvm.BuildConfig
import com.example.matthew.ticketmaster_mvvm.model.event.Event
import com.example.matthew.ticketmaster_mvvm.network.TicketMasterApiService
import com.example.matthew.ticketmaster_mvvm.room.DbWorkerThread
import com.example.matthew.ticketmaster_mvvm.room.EventData
import com.example.matthew.ticketmaster_mvvm.room.EventDataBase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

/**
 * view model for [ListActivity]
 * will handle api calls using LiveData
 *
 * @author Matthew Howells
 */
class ListViewModel @Inject constructor( private val mApiService: TicketMasterApiService) : ViewModel() {

    companion object {
        val TAG : String = ListViewModel::class.java.simpleName.toString()
    }

    private var mDb: EventDataBase? = null
    private lateinit var mDbWorkerThread: DbWorkerThread

    val mEventLiveData = MutableLiveData<ArrayList<Event>>()

    val mFavouriteLiveData = MutableLiveData<ArrayList<EventData>>()
    val mRemoveFavouriteLiveData = MutableLiveData<EventData>()

    private val mDisposable: CompositeDisposable = CompositeDisposable()

    private var mFavouriteList: ArrayList<EventData> = ArrayList()

    /**
     * updates [mEventLiveData] with [Event] retrieved from API call
     * TODO('Update this call to use data retrieved from call')
     */
    fun updateLiveData(events: ArrayList<Event>) {
        mEventLiveData.postValue(events)
    }

    /**
     * initialise room
     */
    fun initialiseRoom(context: Context){
        mDbWorkerThread = DbWorkerThread("dbWorkerThread")
        mDbWorkerThread.start()
        mDb = EventDataBase.getInstance(context)
    }

    /**
     * add [EventData] to [EventDataBase]
     */
    private fun insertEventDataInDb(eventData: EventData) {
        val task = Runnable { mDb?.eventDataDao()?.insert(eventData) }
        val onComplete = Runnable {
            Log.d(TAG,"id: ${eventData.apiId}")
            mFavouriteList.add(eventData)
            updateFavouriteLiveData()
        }
        mDbWorkerThread.postTask(task,onComplete)
    }

    /**
     * add or remove [EventData] from [EventDataBase]
     */
    private fun insertOrDeleteEventDataInDb(eventData: EventData) {
        var id : String = ""
        val task = Runnable {
            val event = mDb?.eventDataDao()?.findEventWithId(eventData.apiId)
            event?.let{
                id=event.apiId
            }
            if(id.equals(""))
                mDb?.eventDataDao()?.insert(eventData)
            else
                mDb?.eventDataDao()?.delete(event!!)
        }
        val onComplete = Runnable {
            if(id.equals("")){
                Log.d(TAG,"favourite id: ${eventData.apiId}")
                mFavouriteList.add(eventData)
                updateFavouriteLiveData()
            }else{
                Log.d(TAG,"unfavourite id: ${eventData.apiId}")
                for(event in mFavouriteList){
                    if(event.apiId.equals(eventData.apiId)) {
                        mFavouriteList.remove(event)
                        updateRemoveFavouriteLiveData(event)
                        break
                    }
                }
            }
        }

        mDbWorkerThread.postTask(task,onComplete)

    }

    /**
     * retrieve [EventData] from [EventDataBase]
     */
    private fun fetchEventDataFromDb() {
        val task = Runnable {
            val eventData =
                    mDb?.eventDataDao()?.getAll()

            if (eventData == null || eventData?.size == 0) {
                //Cache empty
            } else {
                mFavouriteList.clear()
                for(event in eventData){
                    mFavouriteList.add(event)
                }
                updateFavouriteLiveData()
            }
        }
        mDbWorkerThread.postTask(task,null)
    }

    /**
     * updates [mFavouriteLiveData] with [mFavouriteList]
     */
    private fun updateFavouriteLiveData() {
        mFavouriteLiveData.postValue(mFavouriteList)
    }

    /**
     * updates [mRemoveFavouriteLiveData] with [EventData] to be removed
     */
    private fun updateRemoveFavouriteLiveData(eventData: EventData) {
        mRemoveFavouriteLiveData.postValue(eventData)
    }


    /**
     * makes #getEvents api call
     */
    fun fetchEvents() {
        mApiService.getEvents(apiKey = BuildConfig.API_KEY, marketID = "202",size = 50)
                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                .subscribe({it.eventData?.events?.let {
                    updateLiveData(it)
                    updateFavourites()

                } }, { _ ->
                }) {
                }.addTo(mDisposable)
    }

    /**
     * favourites the given event
     */
    fun favouriteEvent(event: Event) {

        var eventData = EventData()
        eventData.apiId = event.id
        eventData.name = event.name
        eventData.date = event.dates.date.date
        insertOrDeleteEventDataInDb(eventData)
        //saves to favourites list
    }

    /**
     * call to retrieve favourites from Database
     */
    fun updateFavourites() {
        fetchEventDataFromDb()
    }

    /**
     * clears all disposables within CompositeDisposable [mDisposable]
     */
    override fun onCleared() {
        super.onCleared()
        mDbWorkerThread.quit()
        mDisposable.clear()
    }

}