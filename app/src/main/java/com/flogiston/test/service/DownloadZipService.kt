package com.flogiston.test.service

import android.app.IntentService
import android.content.Intent
import android.content.Context
import android.net.Uri
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.flogiston.test.data.DownloadZipRepositoryImpl
import com.flogiston.test.domain.repository.DownloadZipRepository
import com.flogiston.test.presentation.extract.ExtractFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import org.koin.android.ext.android.inject
import java.io.*


class DownloadZipService() : IntentService("DownloadZipService") {
    val repositoty : DownloadZipRepository by inject<DownloadZipRepositoryImpl>()
    val disposables = CompositeDisposable()
    override fun onHandleIntent(intent: Intent?) {
        /*val url = intent!!.getStringExtra(ExtractFragment.ZIP_ARCHIVE_URL)
        disposables.add(repositoty.download(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    val file = File.createTempFile(Uri.parse(url).lastPathSegment, null, baseContext.cacheDir)
                    saveResultIntoFile(it, file)
                },
                {}
            ))*/
    }

    fun saveResultIntoFile(responseBody : ResponseBody, file : File){
        val bufferedInputStream = BufferedInputStream(responseBody.byteStream(), 4096)
        val outputStream = FileOutputStream(file)
        val bytes = ByteArray(4096)
        var timeFromLastiteration : Long = 0
        var pastIteration  = System.currentTimeMillis()
        val fileSize = responseBody.contentLength()
        var downloadedSize = 0.0f
        var progress = 0.0f
        var count = 0
        while (count != -1){
            count = bufferedInputStream.use { it.read(bytes) }
            downloadedSize += count
            progress = downloadedSize / fileSize * 100
            outputStream.use { it.write(bytes, 0, count) }
            timeFromLastiteration += System.currentTimeMillis() - pastIteration
            pastIteration = System.currentTimeMillis()
            if(timeFromLastiteration >= 1) {
                sendNotification(progress)
                timeFromLastiteration -= 1
            }
        }
        outputStream.use { it.flush() }
    }

    private fun sendNotification (progress : Float){
        val intent = Intent(ExtractFragment.SHOW_PROGRESS)
        intent.putExtra(DOWNLOADED, progress)
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }

    companion object {
        const val DOWNLOADED = "downloaded"
    }
}
