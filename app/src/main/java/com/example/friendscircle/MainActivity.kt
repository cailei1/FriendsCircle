package com.example.friendscircle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.framework.base.BaseActivity
import com.example.framework.base.BaseUIActivity
import com.example.friendscircle.model.UserInfo
import com.example.friendscircle.presenter.MainPresenter

class MainActivity : BaseUIActivity<MainPresenter>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter!!.getUserInfo()

    }


    fun successBackUserInfo(userInfo: UserInfo?) {
        Log.e("cailei", userInfo?.profileImage)
    }
}