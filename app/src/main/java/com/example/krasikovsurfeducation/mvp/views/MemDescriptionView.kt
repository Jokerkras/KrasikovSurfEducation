package com.example.krasikovsurfeducation.mvp.views

import android.content.Intent
import com.example.krasikovsurfeducation.model.MemDto
import moxy.MvpView

interface MemDescriptionView: MvpView {
    fun closeMemDescription()
    fun showMem(mem: MemDto)
    fun showChooser(intent: Intent)
}