package com.example.krasikovsurfeducation.view.intr

import moxy.MvpView

interface MemListView: MvpView {
    fun refreshList()
    fun addMemToFavorite()
    fun removeMemFromFavorite()
    fun shareMem()
    fun showError()
}