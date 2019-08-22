package com.example.krasikovsurfeducation.repo

import com.example.krasikovsurfeducation.model.AuthInfoDto
import com.example.krasikovsurfeducation.model.LoginUserRequestDto
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object MemRepository {
    private val retrofit = Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl("https://demo3161256.mockable.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val memApi = retrofit.create(MemApi::class.java)

    fun login(loginUserRequestDto: LoginUserRequestDto,
              onSuccess: (AuthInfoDto) -> Unit,
              onError: (Throwable) -> Unit) {
        memApi.login(loginUserRequestDto)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                onSuccess(it)
            }, {
                onError(it)
            })
    }
}