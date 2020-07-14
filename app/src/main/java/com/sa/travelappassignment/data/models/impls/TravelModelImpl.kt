package com.sa.travelappassignment.data.models.impls

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sa.travelappassignment.data.models.BaseModel
import com.sa.travelappassignment.data.models.TravelModel
import com.sa.travelappassignment.data.vos.CountriesAndToursVO
import com.sa.travelappassignment.data.vos.CountryVO
import com.sa.travelappassignment.data.vos.TourVO
import com.sa.travelappassignment.network.responses.GetAllCountriesResponse
import com.sa.travelappassignment.network.responses.GetAllToursResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

object TravelModelImpl : TravelModel, BaseModel() {

    override val countriesLiveData: MutableLiveData<List<CountryVO>>
        get() = MutableLiveData(mTravelDB.travelDao().getAllCountries())
    override val toursLiveData: MutableLiveData<List<TourVO>>
        get() = MutableLiveData(mTravelDB.travelDao().getAllTours())

    override fun getAllCountriesAndTours(): Observable<CountriesAndToursVO> =
        Observable.zip(
            mTravelApi.getAllCountries(),
            mTravelApi.getAllTours(),
            BiFunction<GetAllCountriesResponse, GetAllToursResponse, CountriesAndToursVO> { countries, tours ->

                var countryId = 1
                var tourId = 1

                mTravelDB.travelDao().deleteAllCountries()
                mTravelDB.travelDao().deleteAllTours()
                mTravelDB.travelDao().insertAllCountries(countries.data?.map {it.copy(id = countryId++)} ?: listOf())
                mTravelDB.travelDao().insertAllTours(tours.data?.map {it.copy(id = tourId++)} ?: listOf())
                return@BiFunction CountriesAndToursVO(
                    mTravelDB.travelDao().getAllCountries(),
                    mTravelDB.travelDao().getAllTours())
            }
        )

    override fun getCountryByName(name: String): CountryVO {
        return mTravelDB.travelDao().getCountryByName(name)
    }

    override fun getTourByName(name: String): TourVO {
        return mTravelDB.travelDao().getTourByName(name)
    }

//        override fun getAllCountriesAndTours(): Observable<CountriesAndToursVO> {
//        val countries = mTravelApi.getAllCountries().map { it.data.toList() }.subscribeOn(Schedulers.io())
//        val tours = mTravelApi.getAllTours().map { it.data.toList() }.subscribeOn(Schedulers.io())
//
//        val zipData = Observable.zip(
//            countries,
//            tours,
//            createDataVOModel())
//
//        countries.subscribeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                mTravelDB.travelDao().insertAllCountries(it)
//            }, {
//                Log.e("error", it.localizedMessage)
//            })
//
//        tours.subscribeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                mTravelDB.travelDao().insertAllTours(it)
//            }, {
//                Log.e("error", it.localizedMessage)
//            })
//
//        return zipData
//    }


//    private fun createDataVOModel() : BiFunction<List<CountryVO>, List<TourVO>, CountriesAndToursVO> {
//        val countries = ArrayList<CountryVO>()
//        val tours = ArrayList<TourVO>()
//
//        return BiFunction { one, two ->
//            one.forEach {
//                countries.add(it)
//            }
//            two.forEach {
//                tours.add(it)
//            }
//            CountriesAndToursVO(countries, tours)
//        }
//    }
}