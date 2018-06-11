package com.example.matthew.ticketmaster_mvvm.model.date

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Model class for Date for individual events retrieved from Event Api
 *
 * @author Matthew Howells
 */

@Parcelize
class Date(
        @SerializedName("localDate") val date: String = "",
        @SerializedName("dateTBD") val dateTBD: Boolean = false,
        @SerializedName("dateTBA") val dateTBA: Boolean = false,
        @SerializedName("timeTBA") val timeTBA: Boolean = false,
        @SerializedName("noSpecificTime") val noSpecificTime: Boolean = false

) : Parcelable