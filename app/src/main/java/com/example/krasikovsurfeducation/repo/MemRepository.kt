package com.example.krasikovsurfeducation.repo

import com.example.krasikovsurfeducation.model.LoginUserRequestDto
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MemRepository {
    private val retrofit = Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl("https://demo3161256.mockable.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val memApi = retrofit.create(MemApi::class.java)

    fun login(loginUserRequestDto: LoginUserRequestDto) = memApi.login(loginUserRequestDto)
}