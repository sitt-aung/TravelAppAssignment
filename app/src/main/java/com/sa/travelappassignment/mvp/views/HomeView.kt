package com.sa.travelappassignment.mvp.views

import com.sa.travelappassignment.data.vos.CountryVO
import com.sa.travelappassignment.data.vos.TourVO

interface HomeView : BaseView {

    fun displayCountriesList(countriesList: List<CountryVO>)
    fun displayToursList(toursList: List<TourVO>)
    fun navigateToCountryDetails(name: String)
    fun navigateToTourDetails(name: String)
    fun displayEmptyView()
    fun hideEmptyView()
    fun enableSwipeRefresh()
    fun disableSwipeRefresh()
    fun displayError(error: String)

}