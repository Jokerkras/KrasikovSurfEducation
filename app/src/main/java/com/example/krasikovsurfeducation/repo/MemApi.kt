package com.example.krasikovsurfeducation.repo

import com.example.krasikovsurfeducation.model.AuthInfoDto
import com.example.krasikovsurfeducation.model.LoginUserRequestDto
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.POST

interface MemApi {

    @POST("auth/login")
    fun login(@Body loginUserRequestDto: LoginUserRequestDto): Observable<AuthInfoDto>
}