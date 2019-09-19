package com.flogiston.test.network

import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Streaming
import retrofit2.http.Url

interface DownloadZipService {
    @GET
    @Streaming
    fun downloadArchive(@Url fileUrl : String) : Single<ResponseBody>
}