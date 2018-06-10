package com.example.matthew.ticketmaster_mvvm.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Model class for individual events from the event response
 *
 * @author Matthew Howells
 */

@Parcelize
class Event(
        @SerializedName("name") val name: String = ""

) : Parcelable