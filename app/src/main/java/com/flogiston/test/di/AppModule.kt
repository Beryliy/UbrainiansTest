package com.flogiston.test.di

import com.flogiston.test.BuildConfig
import okhttp3.OkHttpClient
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
}