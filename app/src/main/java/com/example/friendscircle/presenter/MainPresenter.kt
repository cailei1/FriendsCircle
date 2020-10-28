package com.example.friendscircle.presenter

import android.util.Log
import com.example.framework.base.BaseActivity
import com.example.framework.base.BasePresenter
import com.example.friendscircle.MainActivity
import com.example.friendscircle.model.UserInfo
import com.example.friendscircle.services.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainPresenter : BasePresenter<MainActivity>() {


    fun getUserInfo() {
        UserService.userInfo("jsmith").enqueue(object : Callback<UserInfo> {
            override fun onFailure(call: Call<UserInfo>, t: Throwable) {
                Log.e("cailei",t.toString())
            }

            override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
                var userInfo = response.body()
                view.successBackUserInfo(userInfo)
            }

        })
    }


}