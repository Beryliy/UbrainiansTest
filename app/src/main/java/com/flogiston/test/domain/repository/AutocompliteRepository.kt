package com.flogiston.test.domain.repository

import com.flogiston.test.network.autocompliteEntities.Geoname
import io.reactivex.Single

interface AutocompliteRepository {
    fun getSuitableAddresses(query : String, maxRows : Int) : Single<List<Geoname>>
}