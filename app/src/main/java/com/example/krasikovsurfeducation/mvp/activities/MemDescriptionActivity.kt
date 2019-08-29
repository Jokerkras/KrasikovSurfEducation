package com.example.krasikovsurfeducation.mvp.activities

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.bumptech.glide.Glide
import com.example.krasikovsurfeducation.R
import com.example.krasikovsurfeducation.model.MemDto
import com.example.krasikovsurfeducation.mvp.presenters.MemDescriptionPresenter
import com.example.krasikovsurfeducation.mvp.views.MemDescriptionView
import kotlinx.android.synthetic.main.activity_mem_description.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


class MemDescriptionActivity : MvpAppCompatActivity(), MemDescriptionView {

    @InjectPresenter lateinit var memDescriptionPresenter: MemDescriptionPresenter
    lateinit var mem: MemDto
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mem_description)
        mem = intent.getSerializableExtra("mem") as MemDto
        memDescriptionPresenter.mem = mem
        memDescriptionPresenter.showMem()
        btn_close.setOnClickListener { closeMemDescription() }

        //TODO доразобраться
        btn_share.setOnClickListener { }//memDescriptionPresenter.shareMem(getString(R.string.text_share), this, imageView_mem_content.drawable.toBitmap()) }
    }
    override fun closeMemDescription() {
        finish()
        imageView_mem_content.drawable
    }

    override fun showChooser(intent: Intent) {
        startActivity(intent)
    }

    override fun showMem(mem: MemDto) {
        textView_title.text = mem.title
        Glide.with(this).load(mem.photoUtl).into(imageView_mem_content)
        val date = (Date().time - mem.createdDate*1000)/1000/60/60/24
        textView_date.text = resources.getQuantityString(R.plurals.daysGone, date.toInt(), date.toInt())

        textView_description.text = mem.description
        if (mem.isFavorite) imageButton_isFavorite.setImageResource(R.drawable.ic_added_to_favorite)
            else imageButton_isFavorite.setImageResource(R.drawable.ic_not_in_favorite)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        memDescriptionPresenter.onRequestPermissionsResult(requestCode, grantResults, applicationContext)
    }

}
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    