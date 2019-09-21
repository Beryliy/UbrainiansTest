package com.flogiston.test.presentation.autocomplete

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import io.reactivex.subjects.PublishSubject

class AutocompleteValues : BaseObservable() {

    val addressPublishSubject = PublishSubject.create<String>()

    @get:Bindable
    var addressQuery = String()
    set(value) {
        field  = value
        if(!value.isNullOrBlank()){
            addressPublishSubject.onNext(value)
        }
        //notifyPropertyChanged()
    }
}