package com.flogiston.test.network

import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Url

interface ZipService {
    @GET
    fun downloadArchive(@Url fileUrl : String) : Single<ResponseBody>
}