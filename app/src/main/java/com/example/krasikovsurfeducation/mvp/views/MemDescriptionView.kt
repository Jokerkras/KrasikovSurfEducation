package com.example.krasikovsurfeducation.mvp.views

import com.example.krasikovsurfeducation.model.MemDto
import moxy.MvpView

interface MemDescriptionView: MvpView {
    fun closeMemDescription()
    fun shareMem()
    fun showMem(mem: MemDto)
}