package com.example.krasikovsurfeducation.mvp.activities

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.example.krasikovsurfeducation.R

class SplashActivity : AppCompatActivity() {

    val SPLASH_DISPLAY_LENGHT = 300L
    val runnable = Runnable {
        val intent = Intent(applicationContext, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed(runnable, SPLASH_DISPLAY_LENGHT)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}
