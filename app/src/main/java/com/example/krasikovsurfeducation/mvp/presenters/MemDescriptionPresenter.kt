package com.example.krasikovsurfeducation.mvp.presenters

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.example.krasikovsurfeducation.model.MemDto
import com.example.krasikovsurfeducation.mvp.views.MemDescriptionView
import moxy.InjectViewState
import moxy.MvpPresenter
import java.io.File
import java.net.URI
import android.provider.MediaStore.Images
import android.graphics.Bitmap
import java.io.ByteArrayOutputStream


@InjectViewState
class MemDescriptionPresenter: MvpPresenter<MemDescriptionView>(){
    lateinit var mem: MemDto

    fun showMem() {
        viewState.showMem(mem)
    }

    fun shareMem(text: String, context: Context, bitmap: Bitmap) {

        val file = File(mem.photoUtl)
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, mem.description)
            putExtra(Intent.EXTRA_STREAM, getImageUri(context, bitmap))
            type = "image/*"
        }
        viewState.showChooser(Intent.createChooser(sendIntent, text))
    }

    fun getImageUri(inContext: Context, inImage: Bitmap): Uri {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = Images.Media.insertImage(inContext.contentResolver, inImage, "Title", null)
        return Uri.parse(path)
    }
}