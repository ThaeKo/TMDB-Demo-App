package com.mm.samplemovieapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface VideoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(data : UserTable)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUpcoming(data : UpcomingTable)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPopular(data : PopularTable)

    @Query("UPDATE UpcomingTable SET data = :mdata WHERE vId = :id")
    fun updateUpcoming(id : Int,mdata : String)

    @Query("UPDATE PopularTable SET data = :mdata WHERE vId = :id")
    fun updatePopular(id : Int,mdata : String)

    @Query("SELECT data FROM UpcomingTable")
    fun getUpComingList(): MutableList<String>

    @Query("SELECT data FROM PopularTable")
    fun getPopularList(): MutableList<String>
}