package com.example.matthew.ticketmaster_mvvm.model.event

import com.google.gson.annotations.SerializedName

/**
 * Response class for Event Api call, contains the [EventData]
 *
 * @author Matthew Howells
 */

class EventResponse {

    @SerializedName("_embedded")
    val eventData: EventData? = null

}