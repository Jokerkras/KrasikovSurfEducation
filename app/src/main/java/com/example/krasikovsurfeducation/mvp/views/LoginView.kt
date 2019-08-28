package com.example.krasikovsurfeducation.mvp.views

import moxy.MvpView

interface LoginView: MvpView {
    fun startLogin()
    fun startAnimation()
    fun stopAnimation()
    fun showError()
    fun setPasswordVisibility()
    fun openMainActivityAndFinish()
}