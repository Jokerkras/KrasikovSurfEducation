package com.example.krasikovsurfeducation.mvp.views

import com.example.krasikovsurfeducation.model.MemDto
import moxy.MvpView

interface MemListView: MvpView {
    fun refreshList(it: List<MemDto>)
    fun addMemToFavorite()
    fun removeMemFromFavorite()
    fun shareMem()
    fun showBadConnectionError()
    fun stopRefreshing()
}