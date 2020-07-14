package com.sa.travelappassignment.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.sa.travelappassignment.persistence.typeconverters.ScoresAndReviewsTypeConverter
import com.sa.travelappassignment.persistence.typeconverters.StringListTypeConverter

@Entity(tableName = "tours")
@TypeConverters(ScoresAndReviewsTypeConverter::class, StringListTypeConverter::class)
data class TourVO (
    @PrimaryKey (autoGenerate = true)
    val id: Int = 0,
    @SerializedName("name") val name: String = "",
    @SerializedName("description") val description: String = "",
    @SerializedName("location") val location: String = "",
    @SerializedName("average_rating") val averageRating: Double = 0.0,
    @SerializedName("scores_and_reviews") val scoresAndReviewVOS: ArrayList<ScoresAndReviewsVO>? = arrayListOf(),
    @SerializedName("photos") val photos: ArrayList<String>? = arrayListOf()
)