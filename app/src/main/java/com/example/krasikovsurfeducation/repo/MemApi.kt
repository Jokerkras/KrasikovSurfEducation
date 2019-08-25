package com.example.krasikovsurfeducation.repo

import com.example.krasikovsurfeducation.model.MemDto
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MemApi {

    @GET("memes")
    fun memes(@Query("token") token: String): Observable<List<MemDto>>

}