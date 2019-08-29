package com.example.krasikovsurfeducation.repo

import android.util.Log
import com.example.krasikovsurfeducation.model.MemDto
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

class MemRepository @Inject constructor(val retrofit: Retrofit) {

    private val memApi = retrofit.create(MemApi::class.java)

    fun getMemList(): Observable<List<MemDto>> = memApi.memes()

}