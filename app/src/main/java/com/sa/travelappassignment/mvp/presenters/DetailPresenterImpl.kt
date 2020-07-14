package com.sa.travelappassignment.mvp.presenters

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import com.sa.travelappassignment.activities.DetailActivity
import com.sa.travelappassignment.data.models.TravelModel
import com.sa.travelappassignment.data.models.impls.TravelModelImpl
import com.sa.travelappassignment.data.vos.TravelVO
import com.sa.travelappassignment.mvp.views.DetailView

class DetailPresenterImpl : DetailPresenter, AbstractBasePresenter<DetailView>() {

    var mTravelModel: TravelModel = TravelModelImpl

    override fun onUIReady(lifecycleOwner: LifecycleOwner, name: String, travelTable: String) {
        getTravelVO(name, travelTable)
    }

    private fun getTravelVO(name: String, travelTable: String){
        val travelVO  =
            when (travelTable){
                DetailActivity.countriesTable ->{
                    TravelVO.fromCountry(mTravelModel.getCountryByName(name))
                }
                else -> {
                    TravelVO.fromTour(mTravelModel.getTourByName(name))
                }
            }

        mView?.displayDetail(travelVO)
    }
}