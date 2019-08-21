package com.example.krasikovsurfeducation.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.text.InputType
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import android.view.animation.AnimationUtils.loadAnimation
import androidx.core.view.isVisible
import com.example.krasikovsurfeducation.Constants
import com.example.krasikovsurfeducation.R
import com.example.krasikovsurfeducation.model.LoginUserRequestDto
import com.example.krasikovsurfeducation.repo.MemRepository
import com.google.android.material.snackbar.Snackbar
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class LoginActivity: AppCompatActivity() {
    private var on = false
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPref = getSharedPreferences(Constants.APP_PREFERENCES, Context.MODE_PRIVATE)

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
        if (extended_edit_text_login.text.isEmpty() || extended_edit_text_password.text.isEmpty()){
            if(extended_edit_text_login.text.isEmpty()) field_boxes_login.validate()
            if(extended_edit_text_password.text.isEmpty()) field_boxes_password.validate()
            return
        }

        button_login.isEnabled = false
        button_login_text.isVisible = false
        button_login_loader.isVisible = true
        val animation = loadAnimation(applicationContext, R.anim.loader_rotate)
        button_login_loader.startAnimation(animation)
        val user = LoginUserRequestDto(extended_edit_text_login.text.toString(), extended_edit_text_password.text.toString())

        MemRepository.login(user)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                stopLoading()
                Log.d("myOut", it.toString())
                val editor = sharedPref.edit()
                editor.putString(Constants.ACCESS_TOKEN, it.accessToken)
                editor.putInt(Constants.ID, it.userInfo.id)
                editor.putString(Constants.USERNAME, it.userInfo.username)
                editor.putString(Constants.FIRST_NAME, it.userInfo.firstname)
                editor.putString(Constants.LAST_NAME, it.userInfo.lastname)
                editor.putString(Constants.USER_DESCRIPTION, it.userInfo.userDescription)

                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                finish()
            }, {
                it.printStackTrace()
                stopLoading()
                val snackBar = Snackbar.make(button_login, R.string.login_error_text, Snackbar.LENGTH_LONG)
                snackBar.view.setBackgroundColor(resources.getColor(R.color.colorError))
                snackBar.show()
            })
    }

    private fun stopLoading() {
        button_login_loader.clearAnimation()
        button_login_loader.isVisible = false
        button_login_text.isVisible = true
        button_login.isEnabled = true
    }
}