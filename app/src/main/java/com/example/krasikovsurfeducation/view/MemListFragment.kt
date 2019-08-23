package com.example.krasikovsurfeducation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.krasikovsurfeducation.R
import moxy.MvpAppCompatFragment

class MemListFragment: MvpAppCompatFragment() {
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