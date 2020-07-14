package com.sa.travelappassignment.data.vos

data class TravelVO (
    var id: Int = 0,
    var name: String = "",
    val description: String = "",
    val location: String = "",
    val averageRating: Double = 0.0,
    val scoresAndReviewVOS: ArrayList<ScoresAndReviewsVO>? = null,
    val photos: ArrayList<String>? = null
) {
    companion object {
        fun fromCountry(vo: CountryVO) = TravelVO(id = vo.id, name = vo.name, description = vo.description, location = vo.location, averageRating = vo.averageRating, scoresAndReviewVOS = vo.scoresAndReviewVOS, photos = vo.photos)
        fun fromTour(vo: TourVO) = TravelVO(id = vo.id, name = vo.name, description = vo.description, location = vo.location, averageRating = vo.averageRating, scoresAndReviewVOS = vo.scoresAndReviewVOS, photos = vo.photos)
    }
}