package com.example.krasikovsurfeducation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {

    val SPLASH_DISPLAY_LENGHT = 3000L
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
