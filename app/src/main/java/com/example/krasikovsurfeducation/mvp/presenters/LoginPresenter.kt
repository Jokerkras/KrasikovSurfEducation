package com.example.krasikovsurfeducation.mvp.presenters

import com.example.krasikovsurfeducation.model.AuthInfoDto
import com.example.krasikovsurfeducation.model.LoginUserRequestDto
import com.example.krasikovsurfeducation.repo.LoginRepository
import com.example.krasikovsurfeducation.mvp.views.LoginView
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class LoginPresenter @Inject constructor(val loginRepo: LoginRepository): MvpPresenter<LoginView>() {

    fun startLogin(
        loginUserRequestDto: LoginUserRequestDto,
        onSuccess: (AuthInfoDto) -> Unit,
        onFailure: (Throwable) -> Unit
        ) {
        loginRepo.login(loginUserRequestDto, {
            onSuccess(it)
        }, {
            it.printStackTrace()
            onFailure(it)
        })
    }
}