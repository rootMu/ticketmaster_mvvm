package com.example.matthew.ticketmaster_mvvm.model.venue

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
* Model class for individual venues
*
* @author Matthew Howells
*/

@Parcelize
class Venue(
        @SerializedName("name") val name: String = ""

) : Parcelable