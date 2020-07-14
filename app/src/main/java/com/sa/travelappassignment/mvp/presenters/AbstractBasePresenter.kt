package com.sa.travelappassignment.mvp.presenters

import androidx.lifecycle.ViewModel
import com.sa.travelappassignment.mvp.views.BaseView
import io.reactivex.disposables.CompositeDisposable

abstract class AbstractBasePresenter<T : BaseView> : BasePresenter<T>, ViewModel() {

    var mView: T? = null
    val mDisposable: CompositeDisposable = CompositeDisposable()

    override fun initPresenter(view: T) {
        mView = view
    }

    override fun onCleared() {
        super.onCleared()
        mDisposable.clear()
    }
}