package com.example.krasikovsurfeducation.mvp.activities

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.text.InputType
import androidx.core.view.isVisible
import com.example.krasikovsurfeducation.BaseApp
import com.example.krasikovsurfeducation.R
import com.example.krasikovsurfeducation.model.LoginUserRequestDto
import com.example.krasikovsurfeducation.mvp.views.LoginView
import com.example.krasikovsurfeducation.mvp.presenters.LoginPresenter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject


class LoginActivity: MvpAppCompatActivity(), LoginView {
    @InjectPresenter lateinit var loginPresenter: LoginPresenter
    fun provideLoginPresenter() = loginPresenter

    private val PASSWORD_VISIBLITY = "isPasswordVisible"
    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        BaseApp.getAppComponent().inject(this)

        field_boxes_password.endIconImageButton.setOnClickListener { onPasswordVisibilityBtnClick() }

        button_login.setOnClickListener { onClickLoginButton() }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        outState.putBoolean(PASSWORD_VISIBLITY, isPasswordVisible)
        super.onSaveInstanceState(outState, outPersistentState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        isPasswordVisible = savedInstanceState.getBoolean(PASSWORD_VISIBLITY)
        setPasswordVisibility()
    }

    private fun setPasswordVisibility() {
        if(isPasswordVisible) {
            field_boxes_password.setEndIcon(R.drawable.ic_eye_off)
            extended_edit_text_password.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
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

        startLogin()
    }

    override fun startLogin() {
        val user = LoginUserRequestDto(extended_edit_text_login.text.toString(), extended_edit_text_password.text.toString())
        startAnimation()
        loginPresenter.startLogin(user,
            {
                stopAnimation()
                openMainActivityAndFinish()
            }, {
                stopAnimation()
                showError()
            })
    }

    override fun startAnimation() {
        button_login.isEnabled = false
        button_login_text.isVisible = false
        progressBar.isVisible = true
    }

    override fun openMainActivityAndFinish() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun showError() {
        val snackBar = Snackbar.make(button_login, R.string.login_error_text, Snackbar.LENGTH_LONG)
        snackBar.view.setBackgroundColor(resources.getColor(R.color.colorError))
        snackBar.show()
    }

    override fun stopAnimation() {
        progressBar.isVisible = false
        button_login_text.isVisible = true
        button_login.isEnabled = true
    }
}