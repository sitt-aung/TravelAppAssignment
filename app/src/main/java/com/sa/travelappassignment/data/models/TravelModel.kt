package com.sa.travelappassignment.data.models

import androidx.lifecycle.LiveData
import com.sa.travelappassignment.data.vos.CountriesAndToursVO
import com.sa.travelappassignment.data.vos.CountryVO
import com.sa.travelappassignment.data.vos.TourVO
import io.reactivex.Observable

interface TravelModel {

    fun getAllCountriesAndTours(): Observable<CountriesAndToursVO>
    fun getCountryByName(name: String) : CountryVO
    fun getTourByName(name: String) : TourVO
}