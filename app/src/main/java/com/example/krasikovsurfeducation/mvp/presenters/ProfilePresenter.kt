package com.example.krasikovsurfeducation.mvp.presenters

import com.example.krasikovsurfeducation.BaseApp
import com.example.krasikovsurfeducation.dao.MemDao
import com.example.krasikovsurfeducation.mvp.views.ProfileVIew
import com.example.krasikovsurfeducation.repo.UserStorage
import io.reactivex.android.schedulers.AndroidSchedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class ProfilePresenter: MvpPresenter<ProfileVIew>() {

    @Inject lateinit var memDao: MemDao
    @Inject lateinit var userStorage: UserStorage
    init {
        BaseApp.getAppComponent().inject(this)
    }

    fun getMems() {
        // Для провеки тулбара подгружает все мемы
        // Для пдгрузки своих мемов getMems -> getMyMems
        var disp = memDao.getMems()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                viewState.setMems(it)
            }
    }

    fun getUser() {
        viewState.setUser(userStorage.getUser())
    }
}