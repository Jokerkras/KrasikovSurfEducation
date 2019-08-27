package com.example.krasikovsurfeducation.mvp.activities

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.krasikovsurfeducation.BaseApp
import com.example.krasikovsurfeducation.R
import com.example.krasikovsurfeducation.adapter.MemAdapter
import com.example.krasikovsurfeducation.dao.MemDao
import com.example.krasikovsurfeducation.model.MemDto
import com.example.krasikovsurfeducation.mvp.presenters.MemDescriptionPresenter
import com.example.krasikovsurfeducation.mvp.views.MemListView
import com.example.krasikovsurfeducation.mvp.presenters.MemListPresenter
import com.example.krasikovsurfeducation.mvp.views.MemDescriptionView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_mem_list.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import javax.inject.Inject

class MemListFragment: MvpAppCompatFragment(), MemListView, MemDescriptionView {

    @Inject lateinit var memListPresenter: MemListPresenter
    lateinit var adapter: MemAdapter
    lateinit var staggeredLayoutManager: StaggeredGridLayoutManager
    var memList: ArrayList<MemDto> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        BaseApp.getAppComponent().inject(this)
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
        toolBar_mem_list.menu.clear()
        toolBar_mem_list.inflateMenu(R.menu.toolbar_fragment_mem_list_menu)
        refresh_layout_mem_list.setOnRefreshListener { refreshList() }
        //progressBar_mem_download.visibility = View.VISIBLE
        adapter = MemAdapter(memList) {
            val intent = Intent(activity?.applicationContext, MemDescriptionActivity::class.java)
            intent.putExtra("mem", it)
            startActivity(intent)
        }
        staggeredLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView_mem_list.adapter = adapter
        recyclerView_mem_list.layoutManager = staggeredLayoutManager

        memListPresenter.initMems {
            //progressBar_mem_download.visibility = View.INVISIBLE
            adapter.refreshMemList(it)
        }
    }

    fun showErrorOnStart() {
        text_error_first_connection.visibility = View.VISIBLE
    }

    fun openMem(mem: MemDto) {

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
    override fun closeMemDescription() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showMem(mem: MemDto) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}