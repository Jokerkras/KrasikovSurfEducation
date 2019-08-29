package com.example.krasikovsurfeducation.mvp.activities

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
        mainActivityPresenter.onNavigationViewItemClick(it)
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

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container_for_fragment, fragment)
        transaction.commit()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        addMemFragment.activityResult(requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        addMemFragment.requestPermissionsResult(requestCode, permissions, grantResults)
    }
}