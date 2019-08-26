package com.example.krasikovsurfeducation.mvp.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.krasikovsurfeducation.BaseApp
import com.example.krasikovsurfeducation.R
import com.example.krasikovsurfeducation.adapter.MemAdapter
import com.example.krasikovsurfeducation.model.MemDto
import com.example.krasikovsurfeducation.mvp.views.MemListView
import com.example.krasikovsurfeducation.mvp.presenters.MemListPresenter
import com.google.android.material.snackbar.Snackbar
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
        fun newInstance(): MemListFragment {
            return MemListFragment()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initMemFragment()
    }

    override fun refreshList() {
        memListPresenter.getMemes({
            adapter.refreshMemList(it)
            refresh_layout_mem_list.isRefreshing = false
        },
        {
            showError()
        })
    }

    fun initMemFragment() {
        refresh_layout_mem_list.setOnRefreshListener { refreshList() }

        progressBar_mem_download.visibility = View.VISIBLE

        adapter = MemAdapter(memList)
        staggeredLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView_mem_list.adapter = adapter
        recyclerView_mem_list.layoutManager = staggeredLayoutManager

        memListPresenter.getMemes({
            progressBar_mem_download.visibility = View.INVISIBLE
            adapter.refreshMemList(it)
        },
            {
                showErrorOnStart()
            })
    }

    fun showErrorOnStart() {

        text_error_first_connection.visibility = View.VISIBLE
    }

    override fun showError() {
        refresh_layout_mem_list.isRefreshing = false
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