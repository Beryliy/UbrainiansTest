package com.flogiston.test.presentation.autocomplete

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.flogiston.test.domain.repository.AutocompleteRepository
import com.flogiston.test.network.autocompleteEntities.Geoname
import com.flogiston.test.presentation.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class AutocompleteViewModel(
    private val autocompleteRepository : AutocompleteRepository,
    val autocompleteValues: AutocompleteValues
) : BaseViewModel() {

    val liveSuggests = MutableLiveData<List<Geoname>>()

    private fun configureAutocomplite() {
        disposables.add(autocompleteValues.addressPublishSubject
            .debounce(500, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap { autocompleteRepository.getSuitableAddresses(it, NUMBER_OF_SUGGESTS) }
            .singleOrError()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    liveSuggests.value = it
                },
                {
                    Log.d(TAG, "exception: ${it.message}")
                }
            )
        )
    }

    companion object {
        const val TAG = "AutocompleteViewModel"
        const val NUMBER_OF_SUGGESTS = 5
    }
}