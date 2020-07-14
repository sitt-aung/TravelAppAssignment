package com.sa.travelappassignment.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.sa.travelappassignment.delegates.CountryDelegate
import com.sa.travelappassignment.delegates.TourDelegate
import com.sa.travelappassignment.mvp.views.HomeView

interface HomePresenter : BasePresenter<HomeView>, CountryDelegate, TourDelegate {

    fun onSwipeRefresh(lifecycleOwner: LifecycleOwner)
    fun onUIReady(lifecycleOwner: LifecycleOwner)
}