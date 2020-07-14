package com.sa.travelappassignment.dummy

import com.sa.travelappassignment.data.vos.CountryVO
import com.sa.travelappassignment.data.vos.ScoresAndReviewsVO
import com.sa.travelappassignment.data.vos.TourVO

fun getDummyCountries() : List<CountryVO> {
    val myanmar = CountryVO().apply {
        id = 1
        name = "Myanmar"
        description = "Myanmar description"
        location = "Myanmar Location"
        averageRating = 5.0
        photos = arrayListOf("", "")
        scoresAndReviewVOS = arrayListOf(ScoresAndReviewsVO())
    }

    val singapore = CountryVO().apply {
        id = 2
        name = "Singapore"
        description = "Singapore description"
        location = "Singapore Location"
        averageRating = 4.0
        photos = arrayListOf("", "")
        scoresAndReviewVOS = arrayListOf(ScoresAndReviewsVO())
    }

    return arrayListOf(myanmar, singapore)
}

fun getDummyTours() : List<TourVO> {
    val bagan = TourVO().apply {
        id = 1
        name = "Bagan"
        description = "Bagan description"
    }

    val chinaTown = TourVO().apply {
        id = 1
        name = "China Town"
        description = "China Town description"
    }

    return arrayListOf(bagan, chinaTown)
}