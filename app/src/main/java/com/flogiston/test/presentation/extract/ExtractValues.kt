package com.flogiston.test.presentation.extract

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class ExtractValues : BaseObservable() {

    @get:Bindable
    var zipArchiveUrl = String()
    set(value) {
        field = value
        //notifyPropertyChanged()
    }
}