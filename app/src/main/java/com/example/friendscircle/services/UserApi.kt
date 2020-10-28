package com.example.friendscircle.services

import com.example.friendscircle.model.FriendsInfo
import com.example.friendscircle.model.UserInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import com.example.framework.http.retrofit

interface UserApi {
    /**
     * 用户信息接口
     */
    @GET("user/{username}")
    fun userInfo(@Path("username") userName: String?): Call<UserInfo>

    /**
     * 内容信息接口
     */
    @GET("user/{username}/tweets")
    fun userTweets(@Path("username") userName: String?): Call<FriendsInfo>

}

object UserService : UserApi by retrofit.create(UserApi::class.java)