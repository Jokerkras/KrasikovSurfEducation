package com.example.krasikovsurfeducation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.krasikovsurfeducation.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolBar)
        bottomNavigationView.setOnNavigationItemSelectedListener (mOnNavigationItemSelectedListener)
        toolBar.menu.clear()
        toolBar.title = resources.getString(R.string.mem_list_title)
        openFragment(MemListFragment.newInstance())
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        when(it.itemId) {
            R.id.btn_mem_list -> {
                toolBar.menu.clear()
                toolBar.title = resources.getString(R.string.mem_list_title)
                openFragment(MemListFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.btn_add_mem -> {
                toolBar.menu.clear()
                toolBar.title = "Добавление"
                openFragment(MemListFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.btn_profile -> {
                toolBar.menu.clear()
                toolBar.title = "Профиль"
                openFragment(ProfileFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container_for_fragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}