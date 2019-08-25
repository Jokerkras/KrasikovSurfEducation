package com.example.krasikovsurfeducation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.krasikovsurfeducation.BaseApp
import com.example.krasikovsurfeducation.R
import com.example.krasikovsurfeducation.adapter.MemAdapter
import com.example.krasikovsurfeducation.model.MemDto
import com.example.krasikovsurfeducation.view.intr.MemListView
import com.example.krasikovsurfeducation.view.presenter.MemListPresenter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_mem_list.*
import moxy.MvpAppCompatFragment
import javax.inject.Inject

class MemListFragment: MvpAppCompatFragment(), MemListView {
    @Inject lateinit var memListPresenter: MemListPresenter
    lateinit var adapter: MemAdapter
    lateinit var staggeredLayoutManager: StaggeredGridLayoutManager
    var memList: ArrayList<MemDto> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity?.application as BaseApp).getAppComponent().inject(this)

        return inflater.inflate(R.layout.fragment_mem_list, container, false)
    }

    companion object {
        fun newInstance(): MemListFragment{
            return MemListFragment()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        refreshList()
        adapter = MemAdapter(memList)
        staggeredLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView_mem_list.adapter = adapter
        recyclerView_mem_list.layoutManager = staggeredLayoutManager
    }

    override fun refreshList() {
        progressBar_mem_download.isVisible = true
        memListPresenter.getMemes({
            memList.addAll(it)
            adapter.notifyDataSetChanged()
            progressBar_mem_download.isVisible = false
        },
        {
            showError()
        })
    }

    override fun showError() {
        val snackBar = Snackbar.make(recyclerView_mem_list, R.string.bad_connection_error, Snackbar.LENGTH_LONG)
        snackBar.view.setBackgroundColor(resources.getColor(R.color.colorError))
        snackBar.show()
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
}