package com.example.friendscircle.model

import com.google.gson.annotations.SerializedName

data class UserInfo(
    val avatar: String?,
    val nick: String?,
    @SerializedName(value = "profile-image") val profileImage: String?,
    val username: String?
)