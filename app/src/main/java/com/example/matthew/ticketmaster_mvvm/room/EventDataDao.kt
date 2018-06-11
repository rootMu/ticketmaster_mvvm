package com.example.matthew.ticketmaster_mvvm.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query

/**
 * Room Data Access Object used to access [EventData] from [EventDataBase]
 *
 * @author Matthew Howells
 */
@Dao
interface EventDataDao {

    @Query("SELECT * from eventData")
    fun getAll(): List<EventData>

    @Insert(onConflict = REPLACE)
    fun insert(eventData: EventData)

    @Query("SELECT * FROM eventData WHERE apiid LIKE :id LIMIT 1")
    fun findEventWithId(id: String): EventData

    @Delete
    fun delete(event: EventData)

    @Delete
    fun deleteEvents(vararg events: EventData)

    @Query("DELETE from eventData")
    fun deleteAll()
}