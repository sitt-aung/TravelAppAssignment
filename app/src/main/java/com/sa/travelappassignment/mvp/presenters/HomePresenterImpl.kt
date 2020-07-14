package com.sa.travelappassignment.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.sa.travelappassignment.data.models.TravelModel
import com.sa.travelappassignment.data.models.impls.TravelModelImpl
import com.sa.travelappassignment.data.vos.CountriesAndToursVO
import com.sa.travelappassignment.mvp.views.HomeView
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class HomePresenterImpl : HomePresenter, AbstractBasePresenter<HomeView>() {

    var mTravelModel : TravelModel = TravelModelImpl

    override fun onSwipeRefresh(lifecycleOwner: LifecycleOwner) {
        mTravelModel.countriesLiveData.observe(lifecycleOwner, Observer {
            mView?.displayCountriesList(it)
        })
        mTravelModel.toursLiveData.observe(lifecycleOwner, Observer {
            mView?.displayToursList(it)
        })
    }

    override fun onUIReady(lifecycleOwner: LifecycleOwner) {
        getAllCountriesAndTours()

        mTravelModel.countriesLiveData.observe(lifecycleOwner, Observer {
            mView?.disableSwipeRefresh()
            mView?.hideEmptyView()
            mView?.displayCountriesList(it)
        })
        mTravelModel.toursLiveData.observe(lifecycleOwner, Observer {
            mView?.disableSwipeRefresh()
            mView?.hideEmptyView()
            mView?.displayToursList(it)
        })
    }

    override fun onTapCountry(name: String) {
        mView?.navigateToCountryDetails(name)
    }

    override fun onTapTour(name: String) {
        mView?.navigateToTourDetails(name)
    }

    private fun getAllCountriesAndTours () {
        mTravelModel.getAllCountriesAndTours()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .onErrorReturn { CountriesAndToursVO(emptyList(), emptyList()) }
            .subscribe {
                mTravelModel.countriesLiveData.postValue(it.countries)
                mTravelModel.toursLiveData.postValue(it.tours)
            }
            .addTo(mDisposable)
    }
}