package com.sa.travelappassignment.data.models.impls

import androidx.lifecycle.MutableLiveData
import com.sa.travelappassignment.data.models.TravelModel
import com.sa.travelappassignment.data.vos.CountriesAndToursVO
import com.sa.travelappassignment.data.vos.CountryVO
import com.sa.travelappassignment.data.vos.TourVO
import com.sa.travelappassignment.dummy.getDummyCountries
import com.sa.travelappassignment.dummy.getDummyTours
import io.reactivex.Observable

object MockTravelModelImpl : TravelModel  {

    override val countriesLiveData: MutableLiveData<List<CountryVO>>
        get() = MutableLiveData(getDummyCountries())
    override val toursLiveData: MutableLiveData<List<TourVO>>
        get() = MutableLiveData(getDummyTours())

    override fun getAllCountriesAndTours(): Observable<CountriesAndToursVO> =
        Observable.just(CountriesAndToursVO(countries = getDummyCountries(), tours = getDummyTours()))

    override fun getCountryByName(name: String): CountryVO {
        return CountryVO(name = name, description = "Myanmar description")
    }

    override fun getTourByName(name: String): TourVO {
        return TourVO(name = name, description = "Bagan description")
    }
}