package com.example.matthew.ticketmaster_mvvm.model.event

import com.google.gson.annotations.SerializedName

/**
 * Model class for Event Holder class, essentially abstract
 *
 * @author Matthew Howells
 */

class EventData(@SerializedName("events") var events: ArrayList<Event>? = null)