package com.example.krasikovsurfeducation.mvp.presenters

import com.example.krasikovsurfeducation.BaseApp
import com.example.krasikovsurfeducation.dao.MemDao
import com.example.krasikovsurfeducation.model.MemDto
import com.example.krasikovsurfeducation.mvp.views.MemListView
import com.example.krasikovsurfeducation.repo.MemRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class MemListPresenter: MvpPresenter<MemListView>() {
    @Inject lateinit var memRepo: MemRepository
    @Inject lateinit var memDao: MemDao

    init { BaseApp.getAppComponent().inject(this) }
    fun initMems() {
        val request = memDao.getMems()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                viewState.refreshList(it)
            }
    }

    fun refreshList() {
        val request = memRepo.getMemList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe ({
                viewState.stopRefreshing()
                viewState.refreshList(it)
                memDao.insertMems(it)
            },
            {
                viewState.stopRefreshing()
                viewState.showBadConnectionError()
            })
    }
}