package com.example.krasikovsurfeducation.repo

import android.app.Application
import android.content.Context
import android.util.Log
import com.example.krasikovsurfeducation.model.UserInfo
import javax.inject.Inject

class UserStorage  @Inject constructor(val app: Application) {

    private val APP_PREFERENCES = "educationSettings"

    val sharedPreferences = app.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

    val ACCESS_TOKEN = "accessToken"
    val ID = "id"
    val USERNAME = "username"
    val FIRST_NAME = "firstName"
    val LAST_NAME = "lastName"
    val USER_DESCRIPTION = "userDescription"

    init {
        count += 1
    }

    fun saveAccessToken(token: String) {
        val editor = sharedPreferences.edit()
        editor.putString(ACCESS_TOKEN, token)
        editor.apply()
    }

    fun saveUserInfo(userInfo: UserInfo) {
        val editor = sharedPreferences.edit()
        editor.putInt(ID, userInfo.id)
        editor.putString(USERNAME, userInfo.username)
        editor.putString(FIRST_NAME, userInfo.firstname)
        editor.putString(LAST_NAME, userInfo.lastname)
        editor.putString(USER_DESCRIPTION, userInfo.userDescription)
        editor.apply()

        Log.d("myOut", count.toString())
    }

    fun getAccessToken(): String {
        Log.d("myOut", count.toString())

        return sharedPreferences.getString(ACCESS_TOKEN, "")?: ""
    }

    companion object {
        var count = 0
    }
}