package com.sa.travelappassignment.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sa.travelappassignment.data.vos.ScoresAndReviewsVO

class ScoresAndReviewsTypeConverter {

    @TypeConverter
    fun toString(scoresAndReviewsVO: ArrayList<ScoresAndReviewsVO>): String? {
        return Gson().toJson(scoresAndReviewsVO)
    }

    @TypeConverter
    fun toList(scoresAndReviewsJson: String): ArrayList<ScoresAndReviewsVO> {
        val type = object : TypeToken<ArrayList<ScoresAndReviewsVO>>() {}.type
        return Gson().fromJson(scoresAndReviewsJson, type)
    }
}