package com.example.krasikovsurfeducation.mvp.presenters

import android.app.AlertDialog
import android.content.Context
import com.example.krasikovsurfeducation.BaseApp
import com.example.krasikovsurfeducation.R
import com.example.krasikovsurfeducation.dao.MemDao
import com.example.krasikovsurfeducation.mvp.views.ProfileVIew
import com.example.krasikovsurfeducation.repo.LoginRepository
import com.example.krasikovsurfeducation.repo.UserStorage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class ProfilePresenter: MvpPresenter<ProfileVIew>() {

    @Inject lateinit var memDao: MemDao
    @Inject lateinit var userStorage: UserStorage
    @Inject lateinit var loginRepo: LoginRepository

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

    fun logout(context: Context) {
        AlertDialog.Builder(context, R.style.AlertDialogCustom)
            .setTitle(R.string.title_logout_dialog)
            .setNegativeButton(R.string.logout_cancel) { dialog, it ->
            }
            .setPositiveButton(R.string.logout_yes) { dialog, it ->
                loginRepo.logout()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({
                        viewState.logout()
                        userStorage.clearUser()
                    }, {
                        it.printStackTrace()
                        viewState.showConnectionError(R.string.bad_connection_error)
                    })
            }
            .show()
    }
}