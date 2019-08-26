package com.example.krasikovsurfeducation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.krasikovsurfeducation.R
import com.example.krasikovsurfeducation.model.MemDto
import kotlinx.android.synthetic.main.cardview_mem_item.view.*

class MemAdapter(private val memList: ArrayList<MemDto>): RecyclerView.Adapter<MemAdapter.MemHolder>() {

    fun refreshMemList(list: List<MemDto>) {
        memList.clear()
        memList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_mem_item, parent, false)
        return MemHolder(view)
    }

    override fun getItemCount(): Int {
        return memList.count()
    }

    override fun onBindViewHolder(holder: MemHolder, position: Int) {
        val mem = memList[position]
        holder.title.text = mem.title
        if(mem.isFavorite) holder.btnFavotite.setImageResource(R.drawable.ic_added_to_favorite)
            else holder.btnFavotite.setImageResource(R.drawable.ic_not_in_favorite)
        Glide.with(holder.memImage).load(mem.photoUtl).into(holder.memImage)
    }

    class MemHolder(view: View) : RecyclerView.ViewHolder(view) {
        val memImage = view.image_view_mem
        val title = view.text_view_mem_title
        val btnFavotite = view.btn_isFavorite
        val btnShare = view.btn_share_mem
    }
}