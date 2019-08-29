package com.example.krasikovsurfeducation.mvp.activities

import android.content.Intent
import android.os.Bundle
import androidx.core.graphics.drawable.toBitmap
import com.bumptech.glide.Glide
import com.example.krasikovsurfeducation.R
import com.example.krasikovsurfeducation.model.MemDto
import com.example.krasikovsurfeducation.mvp.presenters.MemDescriptionPresenter
import com.example.krasikovsurfeducation.mvp.views.MemDescriptionView
import kotlinx.android.synthetic.main.activity_mem_description.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
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
        btn_share.setOnClickListener { memDescriptionPresenter.shareMem(getString(R.string.text_share), applicationContext, imageView_mem_content.drawable.toBitmap()) }
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
        when(date%10) {
            1L -> textView_date.text = "$date день назад"
            2L, 3L, 4L -> textView_date.text = "$date дня назад"
            else -> textView_date.text = "$date дней назад"
        }

        textView_description.text = mem.description
        if (mem.isFavorite) imageButton_isFavorite.setImageResource(R.drawable.ic_added_to_favorite)
            else imageButton_isFavorite.setImageResource(R.drawable.ic_not_in_favorite)
    }
}
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    