package com.example.matthew.ticketmaster_mvvm.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Matthew on 10/06/2018.
 */

class EventData(@SerializedName("events") var events: ArrayList<Event>? = null)