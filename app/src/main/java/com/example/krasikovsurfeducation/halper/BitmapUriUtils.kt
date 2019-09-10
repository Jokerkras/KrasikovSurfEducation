package com.example.krasikovsurfeducation.halper

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class BitmapUriUtils {
    companion object {
        fun getImageUri(inContext: Context, bitmap: Bitmap): Uri {
            val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            val storageDir: File = inContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
            val file = File.createTempFile(
                "PNG_${timeStamp}_", /* prefix */
                ".png", /* suffix */
                storageDir /* directory */
            )
            val fout = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fout)
            return FileProvider.getUriForFile(
                inContext,
                "com.example.krasikovsurfeducation.fileprovider",
                file
            )
        }
    }
}