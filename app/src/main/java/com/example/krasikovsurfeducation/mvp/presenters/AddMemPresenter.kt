package com.example.krasikovsurfeducation.mvp.presenters

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.net.Uri
import com.example.krasikovsurfeducation.BaseApp
import com.example.krasikovsurfeducation.R
import com.example.krasikovsurfeducation.dao.MemDao
import com.example.krasikovsurfeducation.model.MemDto
import com.example.krasikovsurfeducation.mvp.views.AddMemView
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class AddMemPresenter: MvpPresenter<AddMemView>() {

    @Inject lateinit var memDao: MemDao
    init {
        BaseApp.getAppComponent().inject(this)
    }

    fun selectImage(context: Context){
        //Диалоговое окно
        val uri = Uri.parse("android.resource://com.example.krasikovsurfeducation/" + R.drawable.mem_to_save)
        AlertDialog.Builder(context, R.style.AlertDialogCustom)
            .setTitle(R.string.title_dialog)
            .setPositiveButton(R.string.from_gallery) { dialog, it ->
                viewState.setImage(uri)
            }
            .setNegativeButton(R.string.take_photo) { dialog, it ->
                viewState.setImage(uri)
            }
            .show()

    }

    fun saveMem(mem: MemDto) {
        memDao.insertMem(mem = mem)
    }

    fun prepareMem() {
        viewState.getMemForSave()
    }
}