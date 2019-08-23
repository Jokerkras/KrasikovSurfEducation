package com.example.krasikovsurfeducation.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.krasikovsurfeducation.repo.UserStorage
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetModule(val app: Application) {
    private val BASE_URL = "https://demo3161256.mockable.io/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit{
        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }

    @Provides
    @Singleton
    fun provideUserManage(): UserStorage {
        return UserStorage(app)
    }
}