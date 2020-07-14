package com.sa.travelappassignment.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.sa.travelappassignment.mvp.views.DetailView

interface DetailPresenter : BasePresenter<DetailView> {

    fun onUIReady (lifecycleOwner: LifecycleOwner, name: String, travelTable: String)
}