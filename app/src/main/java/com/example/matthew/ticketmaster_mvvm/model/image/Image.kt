package com.example.matthew.ticketmaster_mvvm.model.image

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Model class for images received through event API
 *
 * @author Matthew Howells
 */

@Parcelize
class Image(
        @SerializedName("ratio") val ratio: String = "",
        @SerializedName("url") val url: String = "",
        @SerializedName("width") val width: Int = 0,
        @SerializedName("height") val height: Int = 0

) : Parcelable