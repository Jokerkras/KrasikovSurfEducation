package com.example.krasikovsurfeducation.mvp.presenters

import com.example.krasikovsurfeducation.mvp.views.MainActivityView
import moxy.InjectViewState
import moxy.MvpPresenter
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@InjectViewState
class MainActivityPresenter: MvpPresenter<MainActivityView>() {
    fun openMemList() {
        viewState.openMemList()
    }
    fun openAddMem() {
        viewState.openAddMem()
    }
    fun openProfile() {
        viewState.openProfile()
    }
}