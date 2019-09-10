package com.example.krasikovsurfeducation.adapter

import android.widget.ImageView
import com.example.krasikovsurfeducation.model.MemDto

interface MemItemClickListener {
    fun onMemClick(mem: MemDto, memImage: ImageView)
}