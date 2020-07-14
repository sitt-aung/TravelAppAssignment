package com.sa.travelappassignment.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sa.travelappassignment.R
import com.sa.travelappassignment.data.models.TravelModel
import com.sa.travelappassignment.data.models.impls.TravelModelImpl
import com.sa.travelappassignment.data.vos.CountryVO
import com.sa.travelappassignment.data.vos.TourVO
import com.sa.travelappassignment.data.vos.TravelVO
import com.sa.travelappassignment.mvp.presenters.DetailPresenter
import com.sa.travelappassignment.mvp.presenters.DetailPresenterImpl
import com.sa.travelappassignment.mvp.views.DetailView
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.collapsing_toolbar_content.*
import kotlinx.android.synthetic.main.detail_content.*

class DetailActivity : BaseActivity(), DetailView {

    private var tableName: String = ""
    private var name: String = ""

    private lateinit var mPresenter: DetailPresenter

    private fun setUpPresenter() {
        mPresenter = ViewModelProviders.of(this).get(DetailPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        name = intent.getStringExtra(IE_TOUR_NAME) ?: ""
        tableName = intent.getStringExtra(IE_TABLE_NAME) ?: ""

        setUpPresenter()
        mPresenter.onUIReady(this, name, tableName)
    }

    private fun bindData(travelVO: TravelVO){
        Glide.with(this)
            .load(travelVO.photos?.get(1))
            .apply(RequestOptions().centerCrop())
            .into(ivDetailImage)

        tvDetailName.text = travelVO.name
        tvDetailLocation.text = travelVO.location
        rbDetailRating.rating = travelVO.averageRating.toFloat()
        tvDetailRating.text = travelVO.averageRating.toString()
        tvDetailDescription.text = travelVO.description
    }

    companion object {
        const val IE_TOUR_NAME = "tourName"
        const val IE_TABLE_NAME = "extra-table-name"

        const val countriesTable = "countries"
        const val toursTable = "tours"

        fun newIntent(context: Context): Intent {
            return Intent(context, DetailActivity::class.java)
        }

        fun newIntent(context: Context, name : String, tableName: String): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(IE_TOUR_NAME, name)
            intent.putExtra(IE_TABLE_NAME, tableName)
            return intent
        }
    }

    override fun displayDetail(travelVO: TravelVO) {
        bindData(travelVO)
    }
}