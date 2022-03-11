package com.mm.samplemovieapp.di

import android.app.Application
import com.google.gson.GsonBuilder
import com.mm.samplemovieapp.database.VideoDao
import com.mm.samplemovieapp.database.VideoDatabase
import com.mm.samplemovieapp.networks.RetroServiceInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    val baseURL = "https://api.themoviedb.org/3/"

    @Singleton
    @Provides
    fun getRetroServiceInstance(retrofit: Retrofit): RetroServiceInstance {
        return retrofit.create(RetroServiceInstance::class.java)
    }


    @Singleton
    @Provides
    fun getRetroInstance(): Retrofit {

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()

            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
        okHttpClient.addInterceptor(logging)
        okHttpClient.interceptors().add(Interceptor { chain ->
            val original = chain.request()

            val request = original.newBuilder()
                .header("Accept", "application/json")
                .build()

            chain.proceed(request)
        })

        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient.build())
            .build()
    }

    @Singleton
    @Provides
    fun getVideoDatabase(context: Application): VideoDatabase {
        return VideoDatabase.getVideoDatabase(context)
    }

    @Singleton
    @Provides
    fun videoDao(videoDatabase: VideoDatabase): VideoDao {
        return videoDatabase.videoDao()
    }
}