package com.sa.travelappassignment.data.vos

import com.google.gson.annotations.SerializedName

data class ScoresAndReviewsVO (
    @SerializedName("name") val name: String = "",
    @SerializedName("score") val score: Double = 0.0,
    @SerializedName("max_score") val maxScore: Double = 0.0,
    @SerializedName("total_reviews") val totalReviews : Int = 0
)