package com.example.krasikovsurfeducation.mvp.views

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType


@StateStrategyType(AddToEndSingleStrategy::class)
interface MainActivityView: MvpView {
    fun openMemList()
    fun openAddMem()
    fun openProfile()
}