package com.example.krasikovsurfeducation.view

import android.os.Bundle
import android.os.Handler
import android.text.InputType
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import android.view.animation.AnimationUtils.loadAnimation
import androidx.core.view.isVisible
import com.example.krasikovsurfeducation.R
import com.example.krasikovsurfeducation.model.LoginUserRequestDto
import com.example.krasikovsurfeducation.repo.MemRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class LoginActivity: AppCompatActivity() {
    private val memRepo = MemRepository()
    private var on = false
    private val SPLASH_DISPLAY_LENGHT = 3000L

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
        val user = LoginUserRequestDto(extended_edit_text_login.text.toString(), extended_edit_text_password.text.toString())
        memRepo.login(user)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                Log.d("myOut","good")
                Log.d("myOut", it.toString())

                button_login_loader.clearAnimation()
                button_login_loader.isVisible = false
                button_login_text.isVisible = true
                button_login.isEnabled = true
            }, {
                it.printStackTrace()

                button_login_loader.clearAnimation()
                button_login_loader.isVisible = false
                button_login_text.isVisible = true
                button_login.isEnabled = true
            })
    }
}