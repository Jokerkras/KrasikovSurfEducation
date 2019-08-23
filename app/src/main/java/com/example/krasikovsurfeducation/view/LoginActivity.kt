package com.example.krasikovsurfeducation.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import android.text.InputType
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import androidx.core.view.isVisible
import com.example.krasikovsurfeducation.BaseApp
import com.example.krasikovsurfeducation.R
import com.example.krasikovsurfeducation.model.AuthInfoDto
import com.example.krasikovsurfeducation.model.LoginUserRequestDto
import com.example.krasikovsurfeducation.repo.LoginRepository
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject


class LoginActivity: AppCompatActivity() {
    private val PASSWORD_VISIBLITY = "isPasswordVisible"
    private var isPasswordVisible = false
    @Inject lateinit var loginRepo: LoginRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //sharedPref = getSharedPreferences(Constants.APP_PREFERENCES, Context.MODE_PRIVATE)

        (application as BaseApp).getAppComponent().inject(this)

        field_boxes_password.endIconImageButton.setOnClickListener { onPasswordVisibilityBtnClick() }
        if (savedInstanceState != null) {
            isPasswordVisible = savedInstanceState.getBoolean(PASSWORD_VISIBLITY)
            setPasswordVisibility()
        }


        button_login.setOnClickListener { onClickLoginButton() }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        outState.putBoolean(PASSWORD_VISIBLITY, isPasswordVisible)
        super.onSaveInstanceState(outState, outPersistentState)
    }

    private fun setPasswordVisibility() {
        if(isPasswordVisible) {
            field_boxes_password.setEndIcon(R.drawable.ic_eye_off)
            extended_edit_text_password.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
        } else {
            field_boxes_password.setEndIcon(R.drawable.ic_eye_on)
            extended_edit_text_password.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        }
    }

    private fun onPasswordVisibilityBtnClick() {
        setPasswordVisibility()
        isPasswordVisible = !isPasswordVisible
    }

    private fun onClickLoginButton() {
        if (extended_edit_text_login.text.isEmpty() || extended_edit_text_password.text.isEmpty()){
            if(extended_edit_text_login.text.isEmpty()) field_boxes_login.validate()
            if(extended_edit_text_password.text.isEmpty()) field_boxes_password.validate()
            return
        }

        startAnimation()

        val user = LoginUserRequestDto(extended_edit_text_login.text.toString(), extended_edit_text_password.text.toString())

        loginRepo.login(user, {
                stopAnimation()
            Log.d("myOut", it.toString())
                openMainActivityAndFinish()
            }, {
                it.printStackTrace()
                stopAnimation()
                showError()
            })
    }

    /*
    private fun saveUser(user: AuthInfoDto) {
        stopAnimation()
    }*/

    private fun startAnimation() {
        button_login.isEnabled = false
        button_login_text.isVisible = false
        progressBar.isVisible = true
    }

    private fun openMainActivityAndFinish() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun showError() {
        val snackBar = Snackbar.make(button_login, R.string.login_error_text, Snackbar.LENGTH_LONG)
        snackBar.view.setBackgroundColor(resources.getColor(R.color.colorError))
        snackBar.show()
    }

    private fun stopAnimation() {
        progressBar.isVisible = false
        button_login_text.isVisible = true
        button_login.isEnabled = true
    }
}