package com.sa.travelappassignment.persistence.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sa.travelappassignment.data.vos.CountryVO
import com.sa.travelappassignment.data.vos.TourVO
import com.sa.travelappassignment.persistence.daos.TravelDao

@Database(entities = [CountryVO::class, TourVO::class], version = 1, exportSchema = false)
abstract class TravelDB: RoomDatabase() {

    companion object {
        const val DBNAME = "Travel.DB"
        var dbInstance: TravelDB? = null

        fun getDBInstance(context: Context): TravelDB {
            when (dbInstance) {
                null -> {
                    dbInstance = Room.databaseBuilder(context, TravelDB::class.java, DBNAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return dbInstance!!
        }
    }

    abstract fun travelDao() : TravelDao
}