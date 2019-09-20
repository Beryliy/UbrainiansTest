package com.flogiston.test.presentation.extract

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

class ExtractValues : BaseObservable() {

    @get:Bindable
    var zipArchiveUrl = String()
    set(value) {
        field = value
        notifyPropertyChanged(BR.zipArchiveUrl)
    }
}