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
    }

    fun getAccessToken(): String {
        return sharedPreferences.getString(ACCESS_TOKEN, "")?: ""
    }

    fun getUser(): UserInfo {
        var user = UserInfo(
            id = sharedPreferences.getInt(ID, 0),
            username = sharedPreferences.getString(USERNAME, "")?: "",
            firstname = sharedPreferences.getString(FIRST_NAME, "")?: "",
            lastname = sharedPreferences.getString(LAST_NAME, "")?: "",
            userDescription = sharedPreferences.getString(USER_DESCRIPTION, "")?: ""
        )
        return user
    }

    fun clearUser() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }
}