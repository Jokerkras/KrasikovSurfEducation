package com.example.krasikovsurfeducation.mvp.presenters

import android.Manifest
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
import android.Manifest.permission
import android.Manifest.permission.*
import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import android.os.Build
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import android.provider.MediaStore.Images
import android.graphics.Bitmap
import android.os.Environment
import java.io.ByteArrayOutputStream
import android.provider.MediaStore
import android.util.Log
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


@InjectViewState
class AddMemPresenter: MvpPresenter<AddMemView>() {
    val GALLERY_REQUEST = 2
    val CAMERA_REQUEST = 1


    @Inject lateinit var memDao: MemDao
    init {
        BaseApp.getAppComponent().inject(this)
    }

    //TODO доразобраться
    fun selectImage(context: Context, activity: Activity){
        //Диалоговое окно
        val uri = Uri.parse("android.resource://com.example.krasikovsurfeducation/" + R.drawable.mem_to_save)
        AlertDialog.Builder(context, R.style.AlertDialogCustom)
            .setTitle(R.string.title_dialog)
            .setPositiveButton(R.string.from_gallery) { dialog, it ->
                viewState.setImage(uri)
                //if(isStoragePermissionGranted(context, READ_EXTERNAL_STORAGE, activity))
                 //   getImageFromGallery()
            }
            .setNegativeButton(R.string.take_photo) { dialog, it ->
                //if(isStoragePermissionGranted(context, CAMERA, activity))
               //     getImageFromCamera()
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


    fun setUriToImageView(bitmap: Bitmap, context: Context) {
        viewState.setImage(getImageUri(context, bitmap))
    }

//READ_EXTERNAL_STORAGE
    fun isStoragePermissionGranted(context: Context, permission: String, activity: Activity): Boolean {
    val per: Int
    per = if(permission == CAMERA)
        CAMERA_REQUEST
    else
        GALLERY_REQUEST
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED) {
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

    fun getImageFromGallery() {
    }

    fun getImageFromCamera() {
        viewState.getImageFromCamera()
    }

    fun onRequestPermissionsResult(requestCode: Int, grantResults: IntArray, context: Context) {
        if(grantResults[0] != PackageManager.PERMISSION_GRANTED)
        {
            Toast.makeText(context, "Вы отказала в разрешении", Toast.LENGTH_LONG).show()
            return
        }
        when(requestCode){
            GALLERY_REQUEST -> {
                getImageFromGallery()
            }
            CAMERA_REQUEST -> {
                getImageFromCamera()
            }
        }
    }

    fun createImageFile(inContext: Context): File? {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = inContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "PNG_${timeStamp}_", /* prefix */
            ".png", /* suffix */
            storageDir
        )
    }

    fun getImageUri(inContext: Context, inImage: Bitmap): Uri {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File = inContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        return Uri.fromFile(File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ))
    }
}