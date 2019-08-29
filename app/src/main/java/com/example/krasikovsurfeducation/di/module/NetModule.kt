package com.example.krasikovsurfeducation.di.module

import android.app.Application
import com.example.krasikovsurfeducation.interceptor.TokenInterceptor
import com.example.krasikovsurfeducation.repo.LoginRepository
import com.example.krasikovsurfeducation.repo.MemRepository
import com.example.krasikovsurfeducation.repo.UserStorage
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetModule(val app: Application) {
    private val BASE_URL = "https://demo3161256.mockable.io/"

    @Provides
    @Singleton
    fun provideInterceptor(): TokenInterceptor = TokenInterceptor(provideUserManage())

    @Provides
    @Singleton
    fun provideOkHttp(tokenInterceptor: TokenInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(tokenInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit{
        val retrofit = Retrofit.Builder()
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }

    @Provides
    @Singleton
    fun provideUserManage(): UserStorage = UserStorage(app)

    @Provides
    @Singleton
    fun provideLoginRepository(): LoginRepository = LoginRepository(provideRetrofit(provideOkHttp(provideInterceptor())))

    @Provides
    @Singleton
    fun provideMemRepository(): MemRepository = MemRepository(provideRetrofit(provideOkHttp(provideInterceptor())))
}