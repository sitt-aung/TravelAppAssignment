package com.sa.travelappassignment

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sa.travelappassignment.data.models.TravelModel
import com.sa.travelappassignment.data.models.impls.MockTravelModelImpl
import com.sa.travelappassignment.data.models.impls.TravelModelImpl
import com.sa.travelappassignment.dummy.getDummyCountries
import com.sa.travelappassignment.dummy.getDummyTours
import com.sa.travelappassignment.mvp.presenters.HomePresenterImpl
import com.sa.travelappassignment.mvp.views.HomeView
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(manifest = Config.NONE)
class HomePresenterImplTest {

    private lateinit var mPresenter : HomePresenterImpl

    @RelaxedMockK
    private lateinit var mView: HomeView

    private lateinit var mTravelModel: TravelModel

    @Before
    fun setUpPresenter() {
        MockKAnnotations.init(this)
        TravelModelImpl.initDatabase(ApplicationProvider.getApplicationContext())
        mTravelModel = MockTravelModelImpl
        mPresenter = HomePresenterImpl()

        mPresenter.initPresenter(mView)
        mPresenter.mTravelModel = this.mTravelModel
    }

    @Test
    fun onTapCountry_callNavigateToCountryDetails() {
        mPresenter.onTapCountry("Myanmar")
        verify {
            mView.navigateToCountryDetails("Myanmar")
        }
    }

    @Test
    fun onTapTour_callNavigateToTourDetails() {
        mPresenter.onTapTour("Bagan")
        verify {
            mView.navigateToTourDetails("Bagan")
        }
    }

    @Test
    fun onUIReady_callDisplayCountryListAndTourList() {
        val lifecycleOwner = mock(LifecycleOwner::class.java)
        val lifecycleRegistry = LifecycleRegistry(lifecycleOwner)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        `when`(lifecycleOwner.lifecycle).thenReturn(lifecycleRegistry)

        mPresenter.onUIReady(lifecycleOwner)

        verify {
            mView.displayCountriesList(getDummyCountries())
            mView.displayToursList(getDummyTours())
        }
    }
}