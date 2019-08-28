package com.example.krasikovsurfeducation.mvp.presenters

import com.example.krasikovsurfeducation.BaseApp
import com.example.krasikovsurfeducation.dao.MemDao
import com.example.krasikovsurfeducation.model.MemDto
import com.example.krasikovsurfeducation.mvp.views.MemListView
import com.example.krasikovsurfeducation.repo.MemRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class MemListPresenter: MvpPresenter<MemListView>() {
    @Inject lateinit var memRepo: MemRepository
    @Inject lateinit var memDao: MemDao

    init { BaseApp.getAppComponent().inject(this) }
    fun initMems(
        saveMemes: (List<MemDto>) -> Unit
    ) {
        memDao.getMems()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                saveMemes(it)
            }
    }

    fun refreshList() {
        memRepo.getMemList({
            viewState.stopRefreshing()
            viewState.refreshList(it)
            memDao.insertMems(it)
        }, {
            viewState.stopRefreshing()
            viewState.showBadConnectionError()
        })
    }
}