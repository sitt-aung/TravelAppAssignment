package com.sa.travelappassignment.data.models

import android.content.Context
import com.sa.travelappassignment.network.api.TravelApi
import com.sa.travelappassignment.persistence.db.TravelDB
import com.sa.travelappassignment.utils.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class BaseModel {

    protected var mTravelApi: TravelApi
    protected lateinit var mTravelDB: TravelDB

    init {
        val mOkHttpClient = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(mOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        mTravelApi = retrofit.create(TravelApi::class.java)
    }

    fun initDatabase(context: Context) {
        mTravelDB = TravelDB.getDBInstance(context)
    }

}