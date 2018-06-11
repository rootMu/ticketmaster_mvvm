package com.example.matthew.ticketmaster_mvvm.model.date

import android.os.Parcelable
import com.example.matthew.ticketmaster_mvvm.model.venue.VenueData
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Model class for Date Holder class, main data inside [Date] with the [timeZone] held in base
 *
 * @author Matthew Howells
 */

@Parcelize
class Dates(
        @SerializedName("start") val date: Date,
        @SerializedName("_embedded") val venues: VenueData? = null,
        @SerializedName("timezone") val timeZone: String = ""

) : Parcelable
