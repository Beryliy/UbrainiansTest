package com.flogiston.test.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import okhttp3.ResponseBody
import java.io.File

interface DownloadZipRepository {
    fun download(pathToArchive : String) : Single<File>
}