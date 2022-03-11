package com.mm.samplemovieapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class UserTable(
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    var first_name : String,
    var last_name : String,
    var email : String,
    var dob : String,
    var nationality : String,
    var country : String,
    var phone : String ?= ""
)