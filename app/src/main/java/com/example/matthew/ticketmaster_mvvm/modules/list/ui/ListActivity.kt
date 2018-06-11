package com.example.matthew.ticketmaster_mvvm.modules.list.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.matthew.ticketmaster_mvvm.ListAdapter
import com.example.matthew.ticketmaster_mvvm.R
import com.example.matthew.ticketmaster_mvvm.model.event.Event
import com.example.matthew.ticketmaster_mvvm.modules.list.viewmodel.ListViewModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_list.*
import javax.inject.Inject

/**
 * Created by Matthew on 10/06/2018.
 */

class ListActivity : DaggerAppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    companion object {
        val TAG: String = this.toString()
    }

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    private var mEventList: ArrayList<Event> = ArrayList()
    private var mFavouriteList: ArrayList<Event> = ArrayList()

    private var mViewModel: ListViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        initialiseViews()
        initialiseViewModel()
        initialiseRoom()
        listenToLiveData()
        listenToFavouriteLiveData()
        listenToRemoveFavouriteLiveData()

        /**
         * Animation won't start on onCreate so post runnable used
         * to start refresh
         */
        swipe_container.post({
            // Fetching data from server
            getEvents()
        })
    }

    /**
     *  Initialises the ui
     */
    private fun initialiseViews() {
        swipe_container.setOnRefreshListener(this)
        swipe_container.setColorSchemeResources(R.color.colorPrimary,
                R.color.colorAccent,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark)

        //set recyclerview
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = ListAdapter(mEventList, { event: Event -> favouriteEvent(event) } )
        list.setHasFixedSize(true)

        favouriteList.layoutManager = LinearLayoutManager(this)
        favouriteList.adapter = ListAdapter(mFavouriteList, { event: Event -> favouriteEvent(event) } )
        list.setHasFixedSize(true)
    }

    /**
     *  Initialises the view model [mViewModel] or reuses the existing one
     */
    private fun initialiseViewModel() {
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(ListViewModel::class.java)
    }

    /**
     *  Initialises Room DataBase
     */
    private fun initialiseRoom() {
        mViewModel?.initialiseRoom(this)
    }

    /**
     * starts listening to live data changes
     */
    private fun listenToLiveData() {
        mViewModel?.mEventLiveData?.observe(this, Observer { eventData ->

            eventData?.let {

                if (it.isEmpty()) {

                    Log.d(TAG, "there is no event Data")

                } else {
                    mEventList.clear()
                    mFavouriteList.clear()
                    it.forEach {
                        mEventList.add(it)
                    }
                    list.adapter.notifyDataSetChanged()
                    swipe_container.isRefreshing = false
                }
            }
        })
    }

    /**
     * starts listening to favourite live data changes
     */
    private fun listenToFavouriteLiveData() {
        mViewModel?.mFavouriteLiveData?.observe(this, Observer { favourites ->

            favourites?.let {

                for (eventData in it) {
                    for (event in mEventList) {
                        if (event.id.equals(eventData.apiId)) {
                            event.favourite = true
                            mEventList.remove(event)
                            mFavouriteList.add(event)
                            break
                        }

                    }
                }
                list.adapter.notifyDataSetChanged()
                favouriteList.adapter.notifyDataSetChanged()
            }
        })
    }

    /**
     * starts listening to remove favourite live data changes
     */
    private fun listenToRemoveFavouriteLiveData() {
        mViewModel?.mRemoveFavouriteLiveData?.observe(this, Observer { favourite ->
            //required to reset favourite
            favourite?.let {
                for (event in mFavouriteList) {
                    if (event.id.equals(it.apiId)) {
                        event.favourite = false
                        mFavouriteList.remove(event)
                        //temporarily add to top of list until sort is done
                        mEventList.add(0,event)
                        break
                    }
                }
            }
            list.adapter.notifyDataSetChanged()
            favouriteList.adapter.notifyDataSetChanged()
        })
    }

    /**
     * asks view model to favourite the clicked event
     */
    private fun favouriteEvent(event: Event) {
        mViewModel?.favouriteEvent(event)
    }

    /**
     * asks set collapsed value of event and updated adapter
     */
    private fun showHideEvent(eventData: Event) {
        for (event in mEventList) {
            if (event.id.equals(eventData.id)) {
                event.collapsed = true
            }
        }
        list.adapter.notifyDataSetChanged()

    }

    /**
     * asks view model to get list of events
     */
    private fun getEvents(){
        mViewModel?.fetchEvents()
    }

    /**
     * called whenever the swipe refresh is needing to refresh
     */
    override fun onRefresh() {
        getEvents()
    }



}
