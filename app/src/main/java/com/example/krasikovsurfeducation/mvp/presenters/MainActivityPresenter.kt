package com.example.krasikovsurfeducation.mvp.presenters

import android.view.MenuItem
import com.example.krasikovsurfeducation.R
import com.example.krasikovsurfeducation.mvp.views.MainActivityView
import moxy.InjectViewState
import moxy.MvpPresenter
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@InjectViewState
class MainActivityPresenter: MvpPresenter<MainActivityView>() {
    fun onNavigationViewItemClick(menuItem: MenuItem): Boolean {
        when(menuItem.itemId) {
            R.id.btn_mem_list -> {openMemList()
                return true
            }
            R.id.btn_add_mem -> {openAddMem()
                return true
            }
            R.id.btn_profile -> {openProfile()
                return true
            }
        }
        return false
    }

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