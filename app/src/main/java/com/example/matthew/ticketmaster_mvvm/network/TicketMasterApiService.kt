package com.example.matthew.ticketmaster_mvvm.network

import com.example.matthew.ticketmaster_mvvm.model.event.EventResponse
import io.reactivex.Flowable
import retrofit2.http.*

/**
 * This contains all the api end points needed
 *
 * @author Matthew Howells
 */
interface TicketMasterApiService {

    @GET("/discovery/v2/events.json")
    fun getEvents(@Query("size") size: Int,
                  @Query("marketId") marketID: String,
                  @Query("apikey") apiKey: String): Flowable<EventResponse>
}

