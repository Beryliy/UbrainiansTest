package com.flogiston.test.presentation.extract

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.flogiston.test.domain.repository.DownloadZipRepository
import com.flogiston.test.presentation.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.File

class ExtractViewModel (private val repository : DownloadZipRepository, val extractValues : ExtractValues) : BaseViewModel() {

    val liveFileList = MutableLiveData<Array<File>>()
    lateinit var dir : File

    fun downloadFile() {
        disposables.add(repository.download(extractValues.zipArchiveUrl)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.d(TAG, it.name)
                    liveFileList.value = it.listFiles()
                    dir = it
                },
                {
                    Log.d(TAG, "exception: $it")
                }
            )
        )
    }

    override fun onCleared() {
        super.onCleared()
        if(dir.isDirectory){
            dir.listFiles()!!.forEach {
                it.delete()
            }
        }
        dir.delete()
    }

    companion object {
        const val TAG = "DEBUGextractViewModel"
    }
}