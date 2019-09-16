package com.example.krasikovsurfeducation.mvp.presenters

import android.Manifest
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import com.example.krasikovsurfeducation.model.MemDto
import com.example.krasikovsurfeducation.mvp.views.MemDescriptionView
import moxy.InjectViewState
import moxy.MvpPresenter
import android.graphics.Bitmap
import android.os.Build
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.krasikovsurfeducation.halper.BitmapUriUtils


@InjectViewState
class MemDescriptionPresenter: MvpPresenter<MemDescriptionView>(){
    lateinit var mem: MemDto
    private val STORAGE_REQUEST = 3
    lateinit var savedBitmap: Bitmap
    var savedText = ""

    fun showMem() {
        viewState.showMem(mem)
    }

    fun shareMem(text: String, activity: Activity, bitmap: Bitmap) {
        savedBitmap = bitmap
        if(isStoragePermissionGranted(Manifest.permission.READ_EXTERNAL_STORAGE, activity))
            sendMem(activity.applicationContext, text)
    }

    fun sendMem(context: Context, text: String){
        savedText = text
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, mem.description)
            putExtra(Intent.EXTRA_STREAM, BitmapUriUtils.getImageUri(context, savedBitmap))
            type = "image/*"
        }
        viewState.showChooser(Intent.createChooser(sendIntent, text))
    }

    fun isStoragePermissionGranted(text: String, activity: Activity): Boolean {
        val per: Int
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(ContextCompat.checkSelfPermission(activity.applicationContext, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                return true
            } else {
                ActivityCompat.requestPermissions(
                    activity,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    STORAGE_REQUEST
                )
                return false
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            sendMem(activity.applicationContext, text)
            return true
        }
    }

    fun onRequestPermissionsResult(requestCode: Int, grantResults: IntArray, context: Context) {
        if(grantResults[0] != PackageManager.PERMISSION_GRANTED)
        {
            Toast.makeText(context, "Вы отказала в разрешении", Toast.LENGTH_LONG).show()
            return
        }
        when(requestCode){
            STORAGE_REQUEST -> {
                sendMem(context, savedText)
            }
        }
    }
}