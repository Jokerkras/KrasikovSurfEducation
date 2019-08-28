package com.example.krasikovsurfeducation.mvp.views

import com.example.krasikovsurfeducation.model.MemDto
import com.example.krasikovsurfeducation.model.UserInfo
import moxy.MvpView

interface ProfileVIew: MvpView {
    fun setMems(mems: List<MemDto>)
    fun setUser(user: UserInfo)
}