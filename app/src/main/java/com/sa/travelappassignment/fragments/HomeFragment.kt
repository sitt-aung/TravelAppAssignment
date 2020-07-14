package com.sa.travelappassignment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sa.travelappassignment.R
import com.sa.travelappassignment.activities.DetailActivity
import com.sa.travelappassignment.adapters.CountriesListAdapter
import com.sa.travelappassignment.adapters.ToursListAdapter
import com.sa.travelappassignment.data.models.TravelModel
import com.sa.travelappassignment.data.models.impls.TravelModelImpl
import com.sa.travelappassignment.data.vos.CountryVO
import com.sa.travelappassignment.data.vos.TourVO
import com.sa.travelappassignment.delegates.CountryDelegate
import com.sa.travelappassignment.delegates.TourDelegate
import com.sa.travelappassignment.views.viewpods.EmptyViewPod
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment(), CountryDelegate, TourDelegate {

    private lateinit var viewPodEmpty: EmptyViewPod

    private val mTravelModel: TravelModel = TravelModelImpl
    private lateinit var mCountriesListAdapter: CountriesListAdapter
    private lateinit var mToursListAdapter: ToursListAdapter

    private var countriesList: MutableLiveData<List<CountryVO>> = MutableLiveData()
    private var toursList: MutableLiveData<List<TourVO>> = MutableLiveData()

    private val compositeDisposable = CompositeDisposable()

    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        setUpSwipeRefresh()
        requestData()
        setUpViewPod()

        countriesList.observe(this, Observer {
            if (it.isEmpty()) showEmptyView()
            else {
                hideEmptyView()
                mCountriesListAdapter.setNewData(it.toMutableList())
            }
        })
        toursList.observe(this, Observer {
            if (it.isEmpty()) showEmptyView()
            else {
                hideEmptyView()
                mToursListAdapter.setNewData(it.toMutableList())
            }
        })
    }

    private fun setUpRecyclerView(){
        mCountriesListAdapter = CountriesListAdapter(this)
        mToursListAdapter = ToursListAdapter(this)

        val countryLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val tourLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        rvCountry.layoutManager = countryLayoutManager
        rvTour.layoutManager = tourLayoutManager

        rvCountry.adapter = mCountriesListAdapter
        rvTour.adapter = mToursListAdapter
    }

    private fun setUpSwipeRefresh() {
        swipeRefreshLayout.setOnRefreshListener { requestData() }
    }

    private fun requestData() {
        swipeRefreshLayout.isRefreshing = true

        mTravelModel.getAllCountriesAndTours()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                swipeRefreshLayout.isRefreshing = false
                hideEmptyView()
                countriesList.postValue(it.countries)
                toursList.postValue(it.tours)
            }, {
                swipeRefreshLayout.isRefreshing = false
                showEmptyView()
                showSnackbar(it.localizedMessage ?: "Unknown Error ...")
            }
            ).addTo(compositeDisposable)
    }

    private fun setUpViewPod() {
        viewPodEmpty = vpEmpty as EmptyViewPod
        viewPodEmpty.setEmptyData(
            getString(R.string.em_no_data),
            "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSZh4mZPiSTrNlfjQZae08CcIR7dQSGkidOu9L4Jq-u5tSR3Nt1"
        )
    }

    private fun showEmptyView() {
        homeLayout.visibility = View.GONE
        vpEmpty.visibility = View.VISIBLE
    }

    private fun hideEmptyView() {
        homeLayout.visibility = View.VISIBLE
        vpEmpty.visibility = View.GONE
    }

    override fun onTapCountry(name: String) {
        startActivity(DetailActivity.newIntent(context!!, name, DetailActivity.countriesTable))
//        startActivity(DetailActivity.newIntent(context!!))
    }

    override fun onTapTour(name: String) {
        startActivity(DetailActivity.newIntent(context!!, name, DetailActivity.toursTable))
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}