package com.example.matthew.ticketmaster_mvvm.room

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Room Data Object used in [EventDataBase]
 *
 * @author Matthew Howells
 */
@Entity(tableName = "eventData")
data class EventData(@PrimaryKey(autoGenerate = true) var id: Int?,
                     @ColumnInfo(name = "name") var name: String,
                     @ColumnInfo(name = "date") var date: String,
                     @ColumnInfo(name = "apiid") var apiId: String
){
    constructor():this(null,"","","")
}