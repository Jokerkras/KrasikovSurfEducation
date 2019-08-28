package com.example.krasikovsurfeducation.mvp.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.krasikovsurfeducation.R
import com.example.krasikovsurfeducation.mvp.presenters.MainActivityPresenter
import com.example.krasikovsurfeducation.mvp.views.MainActivityView
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class MainActivity: MvpAppCompatActivity(), MainActivityView {

    @InjectPresenter lateinit var mainActivityPresenter: MainActivityPresenter

    val memListFragment: MemListFragment by lazy { MemListFragment() }
    val addMemFragment: AddMemFragment by lazy { AddMemFragment() }
    val profileFragment: ProfileFragment by lazy { ProfileFragment() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActivityPresenter.openMemList()

        bottomNavigationView.setOnNavigationItemSelectedListener (mOnNavigationItemSelectedListener)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        when(it.itemId) {
            R.id.btn_mem_list -> {
                mainActivityPresenter.openMemList()
                return@OnNavigationItemSelectedListener true
            }
            R.id.btn_add_mem -> {
                mainActivityPresenter.openAddMem()
                return@OnNavigationItemSelectedListener true
            }
            R.id.btn_profile -> {
                mainActivityPresenter.openProfile()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun openMemList() {
        openFragment(memListFragment)
    }

    override fun openAddMem() {
        openFragment(addMemFragment)
    }

    override fun openProfile() {
        openFragment(profileFragment)
    }

    fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container_for_fragment, fragment)
        transaction.commit()
    }
}