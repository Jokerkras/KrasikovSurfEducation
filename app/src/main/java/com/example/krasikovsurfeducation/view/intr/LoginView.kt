package com.example.krasikovsurfeducation.view.intr

import moxy.MvpView

interface LoginView: MvpView {
    fun startLogin()
    fun startAnimation()
    fun stopAnimation()
    fun showError()
}