package com.flogiston.test.domain.repository

import io.reactivex.Single
import okhttp3.ResponseBody

interface DownloadZipRepository {
    fun download() : Single<ResponseBody>
}