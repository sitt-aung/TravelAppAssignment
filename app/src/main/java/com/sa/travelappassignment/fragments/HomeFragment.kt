package com.sa.travelappassignment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
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
import com.sa.travelappassignment.mvp.presenters.HomePresenter
import com.sa.travelappassignment.mvp.presenters.HomePresenterImpl
import com.sa.travelappassignment.mvp.views.HomeView
import com.sa.travelappassignment.views.viewpods.EmptyViewPod
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment(), HomeView {

    private lateinit var viewPodEmpty: EmptyViewPod
    private lateinit var mPresenter: HomePresenter

    private lateinit var mCountriesListAdapter: CountriesListAdapter
    private lateinit var mToursListAdapter: ToursListAdapter

    private fun setUpPresenter() {
        mPresenter = ViewModelProviders.of(this).get(HomePresenterImpl::class.java)
        mPresenter.initPresenter(this)
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
        setUpPresenter()
        setUpRecyclerView()
        setUpViewPod()
        setUpSwipeRefresh()

        mPresenter.onUIReady(this)
    }

    private fun setUpRecyclerView(){
        mCountriesListAdapter = CountriesListAdapter(mPresenter)
        mToursListAdapter = ToursListAdapter(mPresenter)

        val countryLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val tourLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        rvCountry.layoutManager = countryLayoutManager
        rvTour.layoutManager = tourLayoutManager

        rvCountry.adapter = mCountriesListAdapter
        rvTour.adapter = mToursListAdapter
    }

    private fun setUpSwipeRefresh() {
        swipeRefreshLayout.setOnRefreshListener { mPresenter.onSwipeRefresh(this) }
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

    override fun displayCountriesList(countriesList: List<CountryVO>) {
        mCountriesListAdapter.setNewData(countriesList.toMutableList())
    }

    override fun displayToursList(toursList: List<TourVO>) {
        mToursListAdapter.setNewData(toursList.toMutableList())
    }

    override fun navigateToCountryDetails(name: String) {
        startActivity(DetailActivity.newIntent(context!!, name, DetailActivity.countriesTable))
    }

    override fun navigateToTourDetails(name: String) {
        startActivity(DetailActivity.newIntent(context!!, name, DetailActivity.toursTable))
    }

    override fun displayEmptyView() {
        homeLayout.visibility = View.GONE
        vpEmpty.visibility = View.VISIBLE
    }

    override fun hideEmptyView() {
        homeLayout.visibility = View.VISIBLE
        vpEmpty.visibility = View.GONE
    }

    override fun enableSwipeRefresh() {
        swipeRefreshLayout.isRefreshing = true
    }

    override fun disableSwipeRefresh() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun displayError(error: String) {
        showSnackbar(error)
    }

    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }
}