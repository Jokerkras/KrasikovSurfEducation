package com.example.krasikovsurfeducation.view.presenter

import com.example.krasikovsurfeducation.model.AuthInfoDto
import com.example.krasikovsurfeducation.model.LoginUserRequestDto
import com.example.krasikovsurfeducation.model.MemDto
import com.example.krasikovsurfeducation.repo.LoginRepository
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