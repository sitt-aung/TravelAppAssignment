package com.sa.travelappassignment

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sa.travelappassignment.data.models.TravelModel
import com.sa.travelappassignment.data.models.impls.MockTravelModelImpl
import com.sa.travelappassignment.data.models.impls.TravelModelImpl
import com.sa.travelappassignment.data.vos.TravelVO
import com.sa.travelappassignment.mvp.presenters.DetailPresenterImpl
import com.sa.travelappassignment.mvp.views.DetailView
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
class DetailPresenterImplTest {

    private lateinit var mPresenter: DetailPresenterImpl

    @RelaxedMockK
    private lateinit var mView: DetailView

    private lateinit var mTravelModel: TravelModel

    @Before
    fun setUpPresenter() {
        MockKAnnotations.init(this)
        TravelModelImpl.initDatabase(ApplicationProvider.getApplicationContext())
        mTravelModel = MockTravelModelImpl
        mPresenter = DetailPresenterImpl()

        mPresenter.initPresenter(mView)
        mPresenter.mTravelModel = this.mTravelModel
    }

    @Test
    fun onUIReady_callGetTravelVo() {
        val lifecycleOwner = mock(LifecycleOwner::class.java)
        val lifecycleRegistry = LifecycleRegistry(lifecycleOwner)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        `when`(lifecycleOwner.lifecycle).thenReturn(lifecycleRegistry)

        val travelVo = TravelVO(id = 1, name = "Myanmar")
        mPresenter.onUIReady(lifecycleOwner, "Myanmar", "countries")

        verify {
            mView?.displayDetail(travelVo)
        }
    }
}