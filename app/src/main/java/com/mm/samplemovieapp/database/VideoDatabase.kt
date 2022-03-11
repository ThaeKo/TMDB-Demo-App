package com.mm.samplemovieapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(entities = [UpcomingTable::class,UserTable::class,PopularTable::class],version = 1)
public abstract class VideoDatabase : RoomDatabase() {

    abstract fun videoDao() : VideoDao
    companion object{
        private val DB_NAME = "videos.db"
        private lateinit var INSTANCE : VideoDatabase

        fun getVideoDatabase(context: Context) : VideoDatabase{
            INSTANCE = Room.databaseBuilder(context,VideoDatabase::class.java, DB_NAME)
                .allowMainThreadQueries()
//                .addMigrations(MIGRATION_1_2)
                .build()
            return INSTANCE
        }

        val MIGRATION_1_2: Migration =
            object : Migration(1, 2) {
                override fun migrate(database: SupportSQLiteDatabase) {
                    database.execSQL(
                        "CREATE TABLE IF NOT EXISTS 'UserTable' ('id' INTEGER PRIMARY KEY,'user_id' INTEGER)"
                    )
                }
            }
    }



}