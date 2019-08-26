package com.example.krasikovsurfeducation.mvp.presenters

import com.example.krasikovsurfeducation.model.MemDto
import com.example.krasikovsurfeducation.repo.MemRepository
import javax.inject.Inject

class MemListPresenter @Inject constructor(val memRepo: MemRepository){

    fun getMemes(
        onSuccess: (List<MemDto>) -> Unit,
        onError: (Throwable) -> Unit) {
        memRepo.getMemList({
            onSuccess(it)
        }, {
            onError(it)
        })

    }
}