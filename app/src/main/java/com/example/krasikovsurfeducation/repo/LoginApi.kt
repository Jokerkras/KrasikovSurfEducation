package com.example.krasikovsurfeducation.repo

import com.example.krasikovsurfeducation.model.AuthInfoDto
import com.example.krasikovsurfeducation.model.LoginUserRequestDto
import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {

    @POST("auth/login")
    fun login(@Body loginUserRequestDto: LoginUserRequestDto): Observable<AuthInfoDto>

    @POST("auth/logout")
    fun logout(): Completable
}