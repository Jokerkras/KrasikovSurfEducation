package com.example.krasikovsurfeducation.view.presenter

import android.util.Log
import com.example.krasikovsurfeducation.model.AuthInfoDto
import com.example.krasikovsurfeducation.model.LoginUserRequestDto
import com.example.krasikovsurfeducation.repo.LoginRepository
import com.example.krasikovsurfeducation.repo.MemRepository
import com.example.krasikovsurfeducation.view.intr.LoginView
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class LoginPresenter @Inject constructor(val loginRepo: LoginRepository, val memPresenter: MemRepository): MvpPresenter<LoginView>() {

    fun startLogin(
        loginUserRequestDto: LoginUserRequestDto,
        onSuccess: (AuthInfoDto) -> Unit,
        onError: (Throwable) -> Unit) {
        loginRepo.login(loginUserRequestDto, {
            onSuccess(it)
        }, {
            onError(it)
        })

    }
}