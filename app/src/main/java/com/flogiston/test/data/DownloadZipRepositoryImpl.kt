package com.flogiston.test.data

import android.net.Uri
import com.flogiston.test.domain.repository.DownloadZipRepository
import com.flogiston.test.network.DownloadZipService
import io.reactivex.Completable
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Retrofit
import java.io.BufferedInputStream
import java.io.File

class DownloadZipRepositoryImpl (val downloadZipService : DownloadZipService) : DownloadZipRepository {
    override fun download(pathToArchive : String) = downloadZipService.downloadArchive(pathToArchive)
}