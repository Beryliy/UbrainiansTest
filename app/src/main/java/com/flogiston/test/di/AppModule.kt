package com.flogiston.test.di

import com.flogiston.test.BuildConfig
import com.flogiston.test.application.ApplicationProxy
import com.flogiston.test.data.DownloadZipRepositoryImpl
import com.flogiston.test.domain.repository.DownloadZipRepository
import com.flogiston.test.network.DownloadZipService
import com.flogiston.test.presentation.extract.ExtractValues
import com.flogiston.test.presentation.extract.ExtractViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single { OkHttpClient.Builder().build() }
    single { GsonConverterFactory.create() }
    single { RxJava2CallAdapterFactory.create() }
    single <Retrofit> {
        Retrofit.Builder()
            .baseUrl(BuildConfig.GEONAMES_URL)
            .addConverterFactory(get<GsonConverterFactory>())
            .addCallAdapterFactory(get<RxJava2CallAdapterFactory>())
            .client(get())
            .build()
    }
    single { ApplicationProxy(androidApplication()) }
    single { get<Retrofit>().create(DownloadZipService::class.java) }
    factory { ExtractValues() }
    single{ DownloadZipRepositoryImpl(get(), androidApplication().cacheDir) }
    viewModel { ExtractViewModel(get<DownloadZipRepositoryImpl>(), get()) }
}