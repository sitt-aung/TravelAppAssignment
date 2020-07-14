package com.sa.travelappassignment.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sa.travelappassignment.data.vos.CountryVO
import com.sa.travelappassignment.data.vos.TourVO

@Dao
interface TravelDao {

    @Query("select * from countries")
    fun getAllCountries(): List<CountryVO>

    @Query("select * from tours")
    fun getAllTours(): List<TourVO>

    @Query("select * from countries where name = :name")
    fun getCountryByName(name: String) : CountryVO

    @Query("select * from tours where name = :name")
    fun getTourByName(name: String) : TourVO

    @Query("delete from countries")
    fun deleteAllCountries()

    @Query("delete from tours")
    fun deleteAllTours()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCountries(countries: List<CountryVO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllTours (tours: List<TourVO>)
}