package com.flogiston.test.data

import com.flogiston.test.BuildConfig
import com.flogiston.test.domain.repository.AutocompleteRepository
import com.flogiston.test.network.AutocompleteService
import com.flogiston.test.network.autocompleteEntities.Geoname
import io.reactivex.Observable
import io.reactivex.Single

class AutocompleteRepositoryImpl(val autocompleteService : AutocompleteService) : AutocompleteRepository {
    override fun getSuitableAddresses(query: String, maxRows: Int): Observable<List<Geoname>> {
        return autocompleteService.getSuitableAddreses(
            query,
            maxRows,
            BuildConfig.LANGUAGE_CODE,
            BuildConfig.GEONAMES_USERNAME)
            .map {
                it.geonames
            }
    }
}