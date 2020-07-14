package com.sa.travelappassignment.network.api

import com.sa.travelappassignment.network.responses.GetAllCountriesResponse
import com.sa.travelappassignment.network.responses.GetAllToursResponse
import com.sa.travelappassignment.utils.GET_ALL_COUNTRIES
import com.sa.travelappassignment.utils.GET_ALL_TOURS
import io.reactivex.Observable
import retrofit2.http.GET

interface TravelApi {

    @GET(GET_ALL_TOURS)
    fun getAllTours() : Observable<GetAllToursResponse>

    @GET(GET_ALL_COUNTRIES)
    fun getAllCountries(): Observable<GetAllCountriesResponse>
}