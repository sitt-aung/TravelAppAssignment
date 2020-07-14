package com.sa.travelappassignment.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sa.travelappassignment.R
import com.sa.travelappassignment.data.models.TravelModel
import com.sa.travelappassignment.data.models.impls.TravelModelImpl
import com.sa.travelappassignment.data.vos.CountryVO
import com.sa.travelappassignment.data.vos.TourVO
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.collapsing_toolbar_content.*
import kotlinx.android.synthetic.main.detail_content.*

class DetailActivity : BaseActivity() {

    private val mTravelModel: TravelModel = TravelModelImpl

    private var tableName: String = ""
    private var name: String = ""
    var countryVO: CountryVO? = null
    var tourVO: TourVO? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        name = intent.getStringExtra(IE_TOUR_NAME) ?: ""
        tableName = intent.getStringExtra(IE_TABLE_NAME) ?: ""

        bindData()
    }

    private fun bindData(){
        when (tableName){
            countriesTable -> {
                countryVO = mTravelModel.getCountryByName(name)
                bindCountry(countryVO as CountryVO)
            }
            toursTable -> {
                tourVO = mTravelModel.getTourByName(name)
                bindTour(tourVO as TourVO)
            }
        }
    }

    private fun bindCountry(countryVO: CountryVO){
        Glide.with(this)
            .load(countryVO.photos?.get(1))
            .apply(RequestOptions().centerCrop())
            .into(ivDetailImage)

        tvDetailName.text = countryVO.name
        tvDetailLocation.text = countryVO.location
        rbDetailRating.rating = countryVO.averageRating.toFloat()
        tvDetailRating.text = countryVO.averageRating.toString()
        tvDetailDescription.text = countryVO.description
    }

    private fun bindTour(tourVO: TourVO){
        Glide.with(this)
            .load(tourVO.photos?.get(1))
            .apply(RequestOptions().centerCrop())
            .into(ivDetailImage)

        tvDetailName.text = tourVO.name
        tvDetailLocation.text = tourVO.location
        rbDetailRating.rating = tourVO.averageRating.toFloat()
        tvDetailRating.text = tourVO.averageRating.toString()
        tvDetailDescription.text = tourVO.description
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
}