package com.sa.travelappassignment.network.responses

import com.google.gson.annotations.SerializedName
import com.sa.travelappassignment.data.vos.TourVO

data class GetAllToursResponse (
    @SerializedName("code") val code : Int = 0,
    @SerializedName ("message") val message: String = "",
    @SerializedName ("data") val data: ArrayList<TourVO>? = null
) {
    fun isResponseOk() = (code == 200) && (data != null)
}