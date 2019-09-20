package com.flogiston.test.data

import android.net.Uri
import com.flogiston.test.domain.repository.DownloadZipRepository
import com.flogiston.test.network.DownloadZipService
import io.reactivex.Single
import okhttp3.ResponseBody
import java.io.*
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream

class DownloadZipRepositoryImpl (private val downloadZipService : DownloadZipService, private val cacheDir : File) : DownloadZipRepository {
    override fun download(pathToArchive : String) : Single<String> =
        downloadZipService.downloadArchive(pathToArchive)
            .map {
                downloadZip(it, pathToArchive)
                unzip(Uri.parse(pathToArchive).lastPathSegment!!)
            }

    private fun downloadZip(responseBody : ResponseBody, pathToArchive: String){
        val bufferedInptStream = BufferedInputStream(responseBody.byteStream(), 4096)
        val zipName = Uri.parse(pathToArchive).lastPathSegment
        val file = File.createTempFile(zipName, null, cacheDir)
        val outputStream = FileOutputStream(file)
        val bytes = ByteArray(4096)
        var count = 0
        while(count != -1){
            count = bufferedInptStream.use { it.read(bytes) }
            outputStream.use { it.write(bytes, 0 , count) }
        }
        outputStream.use { it.flush() }
    }

    private fun unzip(zipName : String) : String {
        val path = cacheDir.path
        val fileName = zipName.substring(0, zipName.length - 5)//position before .zip
        val fileInputStream = FileInputStream("$path$fileName")
        val zipInputStream = ZipInputStream(BufferedInputStream(fileInputStream))
        val buffer = ByteArray(4096)
        var zipEntry = zipInputStream.use { it.nextEntry }
        var currentFileName : String
        while (zipEntry != null){
            currentFileName = zipEntry.name
            val pathToCurrent = "${cacheDir.path}$currentFileName"
            if(zipEntry.isDirectory){
                val dir = File(pathToCurrent)
                dir.mkdirs()
                continue
            }
            val fileOutputream = FileOutputStream(pathToCurrent)
            var count = zipInputStream.use{ it.read(buffer) }
            while (count != -1){
                fileOutputream.use { it.write(buffer, 0, count) }
                count = zipInputStream.use{ it.read(buffer) }
            }
            zipEntry = zipInputStream.use { it.nextEntry }
        }
        return "$path$fileName"
    }
}