package com.example.krasikovsurfeducation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.krasikovsurfeducation.R
import com.example.krasikovsurfeducation.view.intr.MemListView
import moxy.MvpAppCompatFragment

class MemListFragment: MvpAppCompatFragment(), MemListView {
    override fun refreshList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addMemToFavorite() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeMemFromFavorite() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun shareMem() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mem_list, container, false)
    }

    companion object {
        fun newInstance(): MemListFragment{
            return MemListFragment()
        }
    }
}