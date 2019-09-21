package com.flogiston.test.presentation.autocomplete

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class AutocompleteValues : BaseObservable() {

    @get:Bindable
    var addressQuery = String()
    set(value) {
        field  = value
        //notifyPropertyChanged()
    }
}