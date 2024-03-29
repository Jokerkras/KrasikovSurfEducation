package com.example.krasikovsurfeducation.model

import com.google.gson.annotations.SerializedName

data class LoginUserRequestDto(
    @SerializedName("login") val login: String,
    @SerializedName("password") val password: String
)