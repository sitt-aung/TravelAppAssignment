package com.sa.travelappassignment.mvp.presenters

import com.sa.travelappassignment.mvp.views.BaseView

interface BasePresenter<T : BaseView> {

    fun initPresenter(view: T)
}