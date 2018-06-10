package com.example.matthew.ticketmaster_mvvm.modules.list.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.matthew.ticketmaster_mvvm.ListAdapter
import com.example.matthew.ticketmaster_mvvm.R
import com.example.matthew.ticketmaster_mvvm.modules.list.viewmodel.ListViewModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_list.*
import javax.inject.Inject

/**
 * Created by Matthew on 10/06/2018.
 */

class ListActivity : DaggerAppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    companion object {
        val TAG : String = this.toString()
    }

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    private var mEventList: ArrayList<String> = ArrayList()

    private var mViewModel: ListViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        initialiseViews()
        initialiseViewModel()
        listenToLiveData()

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
        list.adapter = ListAdapter(mEventList, { eventData: String -> favouriteEvent(eventData) })
        list.setHasFixedSize(true)
    }

    /**
     *  Initialises the view model [mViewModel] or reuses the existing one
     */
    private fun initialiseViewModel() {
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(ListViewModel::class.java)
    }


    /**
     * starts listening to live data changes
     */
    private fun listenToLiveData() {
        mViewModel?.mTicketLiveData?.observe(this, Observer { ticketData ->

            ticketData?.let {

                if (it.isEmpty()) {

                    Log.d(TAG,"there is no ticket Data")

                } else {
                    //update view with data
                }
            }
        })
    }

    /**
     * asks view model to favourite the clicked event
     */
    private fun favouriteEvent(eventData: String){
        mViewModel?.favouriteEvent(eventData)
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
        //Implement view model api call from here
    }



}
