package com.example.krasikovsurfeducation

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.InputType
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import android.view.animation.AnimationUtils.loadAnimation
import androidx.core.view.isVisible


class LoginActivity: AppCompatActivity() {
    private var on = false
    private val SPLASH_DISPLAY_LENGHT = 3000L
    private val runnable = Runnable {
        button_login_loader.clearAnimation()
        button_login_loader.isVisible = false
        button_login_text.isVisible = true
        button_login.isEnabled = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        field_boxes_password.endIconImageButton.setOnClickListener { onClickEye() }

        button_login.setOnClickListener { onClickLoginButton() }
    }

    private fun onClickEye() {
        if(on) {
            field_boxes_password.setEndIcon(R.drawable.ic_eye_off)
            extended_edit_text_password.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
        } else {
            field_boxes_password.setEndIcon(R.drawable.ic_eye_on)
            extended_edit_text_password.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        }
        on = !on
    }

    private fun onClickLoginButton() {
        button_login.isEnabled = false
        button_login_text.isVisible = false
        button_login_loader.isVisible = true
        val animation = loadAnimation(applicationContext, R.anim.loader_rotate)
        button_login_loader.startAnimation(animation)
        Handler().postDelayed(runnable, SPLASH_DISPLAY_LENGHT)
    }
}