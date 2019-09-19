package com.flogiston.test.service

import android.app.IntentService
import android.content.Intent
import android.content.Context
import android.net.Uri
import com.flogiston.test.domain.repository.DownloadZipRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import java.io.*


class DownloadZipService(val repositoty : DownloadZipRepository) : IntentService("DownloadZipService") {

    override fun onHandleIntent(intent: Intent?) {
        val url = intent!!.getStringExtra("zipArchiveUrl")
        repositoty.download(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    val file = File.createTempFile(Uri.parse(url).lastPathSegment, null, baseContext.cacheDir)
                    saveResultIntoFile(it, file)
                },
                {}
            )
    }

    fun saveResultIntoFile(responseBody : ResponseBody, file : File){
        val bufferedInputStream = BufferedInputStream(responseBody.byteStream(), 4096)
        val outputStream = FileOutputStream(file)
        val bytes = ByteArray(4096)
        var count = 0
        while (count != -1){
            count = bufferedInputStream.use { it.read(bytes) }
            outputStream.use { it.write(bytes, 0, count) }
        }
        outputStream.use { it.flush() }
    }
}
