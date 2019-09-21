package com.flogiston.test.presentation.autocomplete

import android.view.View
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import io.reactivex.subjects.PublishSubject

class AutocompleteValues : BaseObservable() {

    val addressPublishSubject = PublishSubject.create<String>()
    var fromKeyboard = true

    @get:Bindable
    var addressQuery = String()
    set(value) {
        field  = value
        if(!value.isNullOrBlank()){
            if(fromKeyboard){
                addressPublishSubject.onNext(value)
                recyclerVisible = View.VISIBLE
            } else {
                recyclerVisible = View.GONE
            }
        } else {
            recyclerVisible = View.GONE
        }
        fromKeyboard = true
        notifyPropertyChanged(BR.addressQuery)
    }

    @get:Bindable
    var recyclerVisible = View.GONE
    set(value) {
        field = value
        notifyPropertyChanged(BR.recyclerVisible)
    }

    fun fillFromAutocomplite(address : String) {
        addressQuery = address
        fromKeyboard = false
    }
}