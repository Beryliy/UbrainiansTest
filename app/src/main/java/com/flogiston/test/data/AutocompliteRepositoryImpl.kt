package com.flogiston.test.data

import com.flogiston.test.BuildConfig
import com.flogiston.test.domain.repository.AutocompliteRepository
import com.flogiston.test.network.AutocompliteService
import com.flogiston.test.network.autocompliteEntities.Geoname
import io.reactivex.Single

class AutocompliteRepositoryImpl(val autocompliteService : AutocompliteService) : AutocompliteRepository {
    override fun getSuitableAddresses(query: String, maxRows: Int): Single<List<Geoname>> {
        return autocompliteService.getSuitableAddreses(
            query,
            maxRows,
            BuildConfig.GEONAMES_USERNAME,
            BuildConfig.LANGUAGE_CODE)
            .map {
                it.geonames
            }
    }
}