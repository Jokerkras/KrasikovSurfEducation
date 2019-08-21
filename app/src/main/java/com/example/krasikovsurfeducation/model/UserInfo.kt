package com.example.krasikovsurfeducation.model

import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("id") val id: Int,
    @SerializedName("username") val username: String,
    @SerializedName("firstName") val firstname: String,
    @SerializedName("lastName") val lastname: String,
    @SerializedName("userDescription") val userDescription: String
)