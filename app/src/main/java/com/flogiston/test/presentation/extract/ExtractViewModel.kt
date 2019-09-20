package com.flogiston.test.presentation.extract

import android.util.Log
import com.flogiston.test.domain.repository.DownloadZipRepository
import com.flogiston.test.presentation.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ExtractViewModel (private val repository : DownloadZipRepository, val extractValues : ExtractValues) : BaseViewModel() {

    fun downloadFile() {
        disposables.add(repository.download(extractValues.zipArchiveUrl)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.d(TAG, "filename: $it")
                },
                {
                    Log.d(TAG, "exception: $it")
                }
            )
        )
    }

    companion object {
        const val TAG = "DEBUGextractViewModel"
    }
}