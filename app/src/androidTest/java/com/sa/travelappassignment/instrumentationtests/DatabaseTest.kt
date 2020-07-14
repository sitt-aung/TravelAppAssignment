package com.sa.travelappassignment.instrumentationtests

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.sa.travelappassignment.data.vos.CountryVO
import com.sa.travelappassignment.persistence.daos.TravelDao
import com.sa.travelappassignment.persistence.db.TravelDB
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class DatabaseTest {

    private lateinit var travelDao: TravelDao
    private lateinit var db : TravelDB

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        db = Room.inMemoryDatabaseBuilder(
            context, TravelDB::class.java
        ).build()

        travelDao = db.travelDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertIntoDbTest() {
        val myanmar = CountryVO().apply {
            id = 5
            name = "Myanmar"
            description = "Myanmar Description"
            scoresAndReviewVOS = arrayListOf()
            photos = arrayListOf()
            location = "Yangon"
        }

        travelDao.insertAllCountries(listOf(myanmar))
        assert(travelDao.getCountryByName(myanmar.name).id == myanmar.id)
    }
}