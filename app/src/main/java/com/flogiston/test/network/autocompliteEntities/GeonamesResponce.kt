package com.flogiston.test.network.autocompliteEntities

data class GeonamesResponce(
    val geonames: List<Geoname>,
    val totalResultsCount: Int
)