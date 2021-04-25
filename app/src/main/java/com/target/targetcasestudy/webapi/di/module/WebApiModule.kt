package com.target.targetcasestudy.webapi.di.module

import android.app.Application
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.target.targetcasestudy.webapi.DealsApi
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class WebApiModule {
    @Provides
    @Singleton
    internal fun providesGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    internal fun provideCache(application: Application): Cache {
        return Cache(File(application.cacheDir, "http-cache"), (10 * 1024 * 1024))
    }

    @Provides
    @Singleton
    internal fun provideOkhttpClient(cache: Cache): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.cache(cache)
        httpClient.addInterceptor(logging)
        httpClient.connectTimeout(30, TimeUnit.SECONDS)
        httpClient.readTimeout(30, TimeUnit.SECONDS)
        return httpClient.build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofitInstance(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .baseUrl("https://api.target.com/")
            .build()
    }

    @Provides
    @Singleton
    internal fun provideDealsRepoApi(retrofit: Retrofit): DealsApi {
        return retrofit.create(DealsApi::class.java)
    }
}