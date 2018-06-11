package com.example.matthew.ticketmaster_mvvm.model.venue

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Model class for Venue Holder class, essentially abstract
 *
 * @author Matthew Howells
 */

@Parcelize
class VenueData(@SerializedName("venues") var venues: ArrayList<Venue>? = null): Parcelable