package com.flogiston.test.data

import android.net.Uri
import android.util.Log
import com.flogiston.test.domain.repository.DownloadZipRepository
import com.flogiston.test.network.DownloadZipService
import io.reactivex.Single
import okhttp3.ResponseBody
import java.io.*
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream

class DownloadZipRepositoryImpl (private val downloadZipService : DownloadZipService, private val cacheDir : File) : DownloadZipRepository {
    override fun download(pathToArchive : String) : Single<File> =
        downloadZipService.downloadArchive(pathToArchive)
            .map {
                val zipFile = downloadZip(it, pathToArchive)
                unzip(zipFile)
            }

    private fun downloadZip(responseBody : ResponseBody, pathToArchive: String) : File{
        val zipName = Uri.parse(pathToArchive).lastPathSegment
        val zipFile = File.createTempFile(zipName, null, cacheDir)
        val bytes = ByteArray(4096)
        var bufferedInputStream : BufferedInputStream? = null
        var fileOutputStream : FileOutputStream? = null
        try {
            bufferedInputStream = BufferedInputStream(responseBody.byteStream(), 4096)
            fileOutputStream = FileOutputStream(zipFile)
            var count = bufferedInputStream.read(bytes)
            while(count != -1){
                fileOutputStream.write(bytes, 0 , count)
                count = bufferedInputStream.read(bytes)
            }
            fileOutputStream.flush()
        }catch (e : IOException){
            Log.d(TAG, "error: ${e.message}")
        }finally {
            bufferedInputStream!!.close()
            fileOutputStream!!.close()
        }
        return zipFile
    }

    private fun unzip(zipFile : File) : File {
        val path = cacheDir.path
        val fileName = zipFile.name.substring(0, zipFile.name.length - 19)//position before .zip
        val file = File.createTempFile(fileName, null, cacheDir)
        val buffer = ByteArray(4096)
        var fileInputStream : FileInputStream? = null
        var zipInputStream : ZipInputStream? = null
        var fileOutputStream : FileOutputStream? = null
        try {
            fileInputStream = FileInputStream(zipFile)
            zipInputStream = ZipInputStream(BufferedInputStream(fileInputStream))
            var currentFileName : String
            var zipEntry = zipInputStream.nextEntry
            while(zipEntry != null){
                currentFileName = zipEntry.name
                val pathToCurrent = "${cacheDir.path}$currentFileName"
                if(zipEntry.isDirectory){
                    val dir = File(pathToCurrent)
                    dir.mkdirs()
                    zipEntry = zipInputStream.nextEntry
                    continue
                }
                fileOutputStream = FileOutputStream(pathToCurrent)
                var count = zipInputStream.read(buffer)
                while (count != -1){
                    fileOutputStream.write(buffer, 0, count)
                    count = zipInputStream.read(buffer)
                }
                fileOutputStream.flush()
                zipEntry = zipInputStream.nextEntry
            }
        } catch (e : IOException) {
            Log.d(TAG, e.message)
        } finally {
            zipFile.delete()
            fileInputStream!!.close()
            zipInputStream!!.close()
            fileOutputStream!!.close()
        }
        return file
    }

    companion object {
        const val TAG = "DownloadZipRepository"
    }
}