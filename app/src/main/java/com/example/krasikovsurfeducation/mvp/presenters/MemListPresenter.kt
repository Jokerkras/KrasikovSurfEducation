package com.example.krasikovsurfeducation.mvp.presenters

import com.example.krasikovsurfeducation.dao.MemDao
import com.example.krasikovsurfeducation.model.MemDto
import com.example.krasikovsurfeducation.repo.MemRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class MemListPresenter @Inject constructor(val memRepo: MemRepository, val memDao: MemDao){

    fun getMemes(
        onSuccess: (List<MemDto>) -> Unit,
        onError: (Throwable) -> Unit) {
        memRepo.getMemList({
            onSuccess(it)
            memDao.insertMems(it)
        }, {
            onError(it)
        })
    }

    fun initMems(
        saveMemes: (List<MemDto>) -> Unit
    ) {
        memDao.getMems()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                saveMemes(it)
            }
    }
}