package com.example.krasikovsurfeducation.mvp.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.krasikovsurfeducation.R
import com.example.krasikovsurfeducation.adapter.MemAdapter
import com.example.krasikovsurfeducation.adapter.MemItemClickListener
import com.example.krasikovsurfeducation.model.MemDto
import com.example.krasikovsurfeducation.model.UserInfo
import com.example.krasikovsurfeducation.mvp.presenters.ProfilePresenter
import com.example.krasikovsurfeducation.mvp.views.ProfileVIew
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_mem_list.recyclerView_mem_list
import kotlinx.android.synthetic.main.fragment_profile.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class ProfileFragment: MvpAppCompatFragment(), ProfileVIew, MemItemClickListener {

    @InjectPresenter lateinit var profilePresenter: ProfilePresenter
    lateinit var adapter: MemAdapter
    lateinit var staggeredLayoutManager: StaggeredGridLayoutManager
    var memList: ArrayList<MemDto> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = MemAdapter(memList, this)
        staggeredLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView_mem_list.adapter = adapter
        recyclerView_mem_list.layoutManager = staggeredLayoutManager

        profilePresenter.getUser()
        profilePresenter.getMems()
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        toolBar_small.inflateMenu(R.menu.menu_profile)
        toolBar_small.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.btn_logout -> {
                    profilePresenter.logout(context!!)
                    return@setOnMenuItemClickListener true
                }
                R.id.btn_about -> {
                    Toast.makeText(context, "SurfEducationProject", Toast.LENGTH_LONG).show()
                    return@setOnMenuItemClickListener true
                }
            }
            false
        }
    }

    override fun setMems(mems: List<MemDto>) {
        adapter.refreshMemList(mems)
    }

    override fun setUser(user: UserInfo) {
        textView_large_login.text = user.username
        textView_large_description.text = user.userDescription
    }

    override fun logout() {
        val intent = Intent(context, LoginActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

    override fun showConnectionError(badConnectionError: Int) {
        val snackBar = Snackbar.make(recyclerView_mem_list, R.string.bad_connection_error, Snackbar.LENGTH_LONG)
        snackBar.view.setBackgroundColor(resources.getColor(R.color.colorError))
        snackBar.show()
    }

    override fun onMemClick(mem: MemDto, memImage: ImageView) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val intent = Intent(activity?.applicationContext, MemDescriptionActivity::class.java)
            intent.putExtra("mem", mem)

            val image = androidx.core.util.Pair<View, String>(memImage, "memImage")
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                requireActivity(), image
            )
            startActivity(intent, options.toBundle())
        } else {
            val intent = Intent(activity?.applicationContext, MemDescriptionActivity::class.java)
            intent.putExtra("mem", mem)
            startActivity(intent)
        }
    }
}