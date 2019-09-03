package com.example.krasikovsurfeducation.mvp.views

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import com.example.krasikovsurfeducation.model.MemDto
import moxy.MvpView

interface AddMemView: MvpView {
    fun setImage(image: Uri)
    fun getMemForSave()
    fun opemMemList()
    fun getImageFromCamera()
    fun getImageFromGallery()
}
