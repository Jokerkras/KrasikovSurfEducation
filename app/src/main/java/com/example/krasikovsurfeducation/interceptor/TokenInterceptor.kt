package com.example.krasikovsurfeducation.interceptor

import com.example.krasikovsurfeducation.repo.UserStorage
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class TokenInterceptor @Inject constructor(val userStorage: UserStorage): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val headers = originalRequest.headers.newBuilder()
            .add("accessToken", userStorage.getAccessToken())
            .build()
        val newRequest = originalRequest.newBuilder()
            .headers(headers)
            .build()
        return chain.proceed(newRequest)
    }
}