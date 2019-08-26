package com.example.krasikovsurfeducation.mvp.presenters

import com.example.krasikovsurfeducation.model.MemDto
import com.example.krasikovsurfeducation.mvp.views.MemDescriptionView
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class MemDescriptionPresenter: MvpPresenter<MemDescriptionView>(){
    lateinit var mem: MemDto

    fun showMem() {
        viewState.showMem(mem)
    }

}