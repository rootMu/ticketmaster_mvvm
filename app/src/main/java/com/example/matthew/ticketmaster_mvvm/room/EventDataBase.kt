package com.example.matthew.ticketmaster_mvvm.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

/**
 * Room Database to store [EventData]
 *
 * @author Matthew Howells
 */
@Database(entities = arrayOf(EventData::class), version = 1)
abstract class EventDataBase : RoomDatabase() {

    abstract fun eventDataDao(): EventDataDao

    companion object {
        private var INSTANCE: EventDataBase? = null

        fun getInstance(context: Context): EventDataBase? {
            if (INSTANCE == null) {
                synchronized(EventDataBase::class) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            EventDataBase::class.java, "event.db")
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}