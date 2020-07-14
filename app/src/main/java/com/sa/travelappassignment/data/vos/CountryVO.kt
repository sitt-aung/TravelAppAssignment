package com.sa.travelappassignment.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.sa.travelappassignment.persistence.typeconverters.ScoresAndReviewsTypeConverter
import com.sa.travelappassignment.persistence.typeconverters.StringListTypeConverter

@Entity(tableName = "countries")
@TypeConverters(ScoresAndReviewsTypeConverter::class, StringListTypeConverter::class)
data class CountryVO (
    @PrimaryKey (autoGenerate = true)
    var id: Int = 0,
    @SerializedName("name") var name: String = "",
    @SerializedName("description") var description: String = "",
    @SerializedName("location") var location: String = "",
    @SerializedName("average_rating") var averageRating: Double = 0.0,
    @SerializedName("scores_and_reviews") var scoresAndReviewVOS: ArrayList<ScoresAndReviewsVO>? = null,
    @SerializedName("photos") var photos: ArrayList<String>? = null
)