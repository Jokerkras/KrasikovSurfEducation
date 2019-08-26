package com.example.krasikovsurfeducation.mvp.views

import moxy.MvpView

interface MemListView: MvpView {
    fun refreshList()
    fun addMemToFavorite()
    fun removeMemFromFavorite()
    fun shareMem()
    fun showError()
}