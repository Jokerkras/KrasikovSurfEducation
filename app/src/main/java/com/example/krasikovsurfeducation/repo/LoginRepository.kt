package com.example.krasikovsurfeducation.repo

import android.util.Log
import com.example.krasikovsurfeducation.model.AuthInfoDto
import com.example.krasikovsurfeducation.model.LoginUserRequestDto
import com.example.krasikovsurfeducation.model.MemDto
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

class LoginRepository  @Inject constructor(val retrofit: Retrofit, val userStorage: UserStorage){

    private val loginApi = retrofit.create(LoginApi::class.java)

    fun login(loginUserRequestDto: LoginUserRequestDto,
              onSuccess: (AuthInfoDto) -> Unit,
              onError: (Throwable) -> Unit) {
        loginApi.login(loginUserRequestDto)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                Log.d("myOut", it.toString())
                userStorage.saveAccessToken(it.accessToken)
                userStorage.saveUserInfo(it.userInfo)
                onSuccess(it)
            }, {
                onError(it)
            })
    }

}