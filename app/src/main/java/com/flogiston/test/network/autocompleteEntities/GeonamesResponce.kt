package com.flogiston.test.network.autocompleteEntities

data class GeonamesResponce(
    val geonames: List<Geoname>,
    val totalResultsCount: Int
)