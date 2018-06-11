package com.example.matthew.ticketmaster_mvvm.model.event

import android.os.Parcelable
import com.example.matthew.ticketmaster_mvvm.model.venue.VenueData
import com.example.matthew.ticketmaster_mvvm.model.date.Dates
import com.example.matthew.ticketmaster_mvvm.model.image.Image
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Model class for individual events from the event response
 *
 * @author Matthew Howells
 */

@Parcelize
class Event(
        @SerializedName("name") val name: String = "",
        @SerializedName("id") val id: String = "",
        @SerializedName("_embedded") val venues: VenueData? = null,
        @SerializedName("dates") val dates: Dates,
        @SerializedName("images") val images: ArrayList<Image>? = null,
        @SerializedName("info") val info: String = "",
        @SerializedName("pleaseNote") val pleaseNote: String = ""

) : Parcelable