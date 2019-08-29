package com.example.krasikovsurfeducation.mvp.presenters

import com.example.krasikovsurfeducation.BaseApp
import com.example.krasikovsurfeducation.model.AuthInfoDto
import com.example.krasikovsurfeducation.model.LoginUserRequestDto
import com.example.krasikovsurfeducation.repo.LoginRepository
import com.example.krasikovsurfeducation.mvp.views.LoginView
import com.example.krasikovsurfeducation.repo.UserStorage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class LoginPresenter: MvpPresenter<LoginView>() {

    @Inject lateinit var loginRepo: LoginRepository
    @Inject lateinit var userStorage: UserStorage

    init {
        BaseApp.getAppComponent().inject(this)
    }

    fun startLogin(loginUserRequestDto: LoginUserRequestDto) {
        viewState.startAnimation()
        val request = loginRepo.login(loginUserRequestDto)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                userStorage.saveAccessToken(it.accessToken)
                userStorage.saveUserInfo(it.userInfo)
                viewState.stopAnimation()
            }, {
                it.printStackTrace()
                viewState.stopAnimation()
                viewState.showError()
            })
    }

    fun onClickPasswordVisibility() {
        viewState.setPasswordVisibility()
    }
}