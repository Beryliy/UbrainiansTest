package com.flogiston.test.domain.repository

import com.flogiston.test.network.autocompleteEntities.Geoname
import io.reactivex.Single

interface AutocompleteRepository {
    fun getSuitableAddresses(query : String, maxRows : Int) : Single<List<Geoname>>
}