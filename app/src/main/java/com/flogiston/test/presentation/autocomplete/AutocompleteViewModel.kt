package com.flogiston.test.presentation.autocomplete

import com.flogiston.test.domain.repository.AutocompleteRepository
import com.flogiston.test.presentation.BaseViewModel

class AutocompleteViewModel(
    private val autocompliteRepository : AutocompleteRepository,
    val autocompleteValues: AutocompleteValues
) : BaseViewModel() {

    fun autocomplite(){

    }
}