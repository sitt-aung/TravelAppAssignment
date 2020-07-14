package com.sa.travelappassignment.data.vos

data class CountriesAndToursVO (
    var countries: List<CountryVO> = listOf(),
    var tours: List<TourVO> = listOf()
)