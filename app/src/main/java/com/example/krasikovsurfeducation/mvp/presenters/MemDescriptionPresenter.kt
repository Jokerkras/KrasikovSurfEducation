package com.example.krasikovsurfeducation.mvp.presenters

import android.Manifest
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import com.example.krasikovsurfeducation.model.MemDto
import com.example.krasikovsurfeducation.mvp.views.MemDescriptionView
import moxy.InjectViewState
import moxy.MvpPresenter
import java.io.File
import android.graphics.Bitmap
import android.os.Build
import android.os.Environment
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*
import androidx.core.content.FileProvider




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
        if(isStoragePermissionGranted(Manifest.permission.READ_EXTERNAL_STORAGE, activity))
            sendMem(bitmap,activity.applicationContext, text)
    }

    fun sendMem(bitmap: Bitmap, context: Context, text: String){
        savedBitmap = bitmap
        savedText = text
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, mem.description)
            putExtra(Intent.EXTRA_STREAM, getImageUri(context, bitmap))
            type = "image/*"
        }
        viewState.showChooser(Intent.createChooser(sendIntent, text))
    }

    fun getImageUri(inContext: Context, bitmap: Bitmap): Uri {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File = inContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        val file = File.createTempFile(
            "PNG_${timeStamp}_", /* prefix */
            ".png", /* suffix */
            storageDir /* directory */
        )
        val fOut = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut)
        fOut.flush()
        fOut.close()
        return FileProvider.getUriForFile(
            inContext,
            inContext.packageName + ".com.example.krasikovsurfeducation.provider",
            file
        )
    }

    fun isStoragePermissionGranted(permission: String, activity: Activity): Boolean {
        val per: Int
        per = if(permission == WRITE_EXTERNAL_STORAGE)
            STORAGE_REQUEST
        else
            STORAGE_REQUEST
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(ContextCompat.checkSelfPermission(activity.applicationContext, permission) == PackageManager.PERMISSION_GRANTED) {
                return true
            } else {
                ActivityCompat.requestPermissions(
                    activity,
                    arrayOf(permission),
                    per
                )
                return false
            }
        } else { //permission is automatically granted on sdk<23 upon installation
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
                sendMem(savedBitmap, context, savedText)
            }
        }
    }
}