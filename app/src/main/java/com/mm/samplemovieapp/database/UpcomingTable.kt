package com.mm.samplemovieapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class UpcomingTable(
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    var vId : Int,
    var data : String
)