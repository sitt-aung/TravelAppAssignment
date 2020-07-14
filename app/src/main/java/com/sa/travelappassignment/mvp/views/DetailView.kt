package com.sa.travelappassignment.mvp.views

import com.sa.travelappassignment.data.vos.TravelVO

interface DetailView : BaseView {
    fun displayDetail (travelVO: TravelVO)
}