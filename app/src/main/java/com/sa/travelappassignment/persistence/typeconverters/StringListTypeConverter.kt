package com.sa.travelappassignment.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StringListTypeConverter {

    @TypeConverter
    fun toString(scoresAndReviewsVO: ArrayList<String>): String {
        return Gson().toJson(scoresAndReviewsVO)
    }

    @TypeConverter
    fun toStringList(stringListJson: String): ArrayList<String> {
        val type = object : TypeToken<ArrayList<String>>() {}.type
        return Gson().fromJson(stringListJson, type)
    }
}