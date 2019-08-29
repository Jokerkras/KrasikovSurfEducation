package com.example.krasikovsurfeducation.mvp.activities

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.example.krasikovsurfeducation.R
import com.example.krasikovsurfeducation.model.MemDto
import com.example.krasikovsurfeducation.mvp.presenters.AddMemPresenter
import com.example.krasikovsurfeducation.mvp.views.AddMemView
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_mem_description.*
import kotlinx.android.synthetic.main.fragment_add_mem.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import java.util.*
import java.util.concurrent.TimeUnit

class AddMemFragment : MvpAppCompatFragment(), AddMemView {

    @InjectPresenter lateinit var addMemPresenter: AddMemPresenter
    var mem = MemDto(id = null, title = "", description = "", createdDate = 0, photoUtl = "", isFavorite = false, isMy = true)
    lateinit var titleChange: Disposable
    lateinit var descriptionChange: Disposable

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_mem, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_add_image.setOnClickListener { addMemPresenter.selectImage(context!!, activity!!) }
        titleChange = editText_title.textChanges()
            .debounce(1, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { setEnableOnCreate() }
        descriptionChange = editText_description.textChanges()
            .debounce(1, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { setEnableOnCreate() }
        btn_create_mem.setOnClickListener { addMemPresenter.prepareMem() }
    }

    override fun getMemForSave(){
        mem.title = editText_title.text.toString()
        mem.description = editText_description.text.toString()
        mem.createdDate = Date().time / 1000
        addMemPresenter.saveMem(mem)
    }

    override fun setImage(image: Uri) {
        mem.photoUtl = image.toString()
        Glide.with(this).load(image).into(imageView_mem_to_create)
        setEnableOnCreate()
    }

    fun requestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        addMemPresenter.onRequestPermissionsResult(requestCode, grantResults, context!!)
    }

    fun setEnableOnCreate() {
        if(editText_title.text!!.count() > 140) editText_title.error = "Слишком длинный заголовок!"
        if(editText_description.text!!.count() > 1000) editText_title.error = "Слишком большое описание!"
        btn_create_mem.isEnabled =
            !(editText_title.text!!.isEmpty() or editText_description.text!!.isEmpty() or (mem.photoUtl == ""))
    }

    override fun opemMemList() {
    }

    override fun onDestroyView() {
        super.onDestroyView()
        titleChange.dispose()
        descriptionChange.dispose()
    }

    fun activityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK)
            when(requestCode - 65536){
                addMemPresenter.CAMERA_REQUEST -> {
                    val image = data?.extras?.get("data") as Bitmap
                    addMemPresenter.setUriToImageView(image, context!!)
                }
                addMemPresenter.GALLERY_REQUEST -> {}
            }
    }

    override fun getImageFromCamera() {
        val file = addMemPresenter.createImageFile(context!!)
        file?.also {
            val photoURI: Uri = FileProvider.getUriForFile(
                context!!,
                "com.example.android.krasikovsurfeducation",
                it
            )
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            startActivityForResult(cameraIntent, addMemPresenter.CAMERA_REQUEST)
        }
    }

    override fun getImageFromGallery() {
        //val cameraIntent = Intent(MediaStore.)
        //startActivityForResult(cameraIntent, CAMERA_REQUEST)
    }
}
