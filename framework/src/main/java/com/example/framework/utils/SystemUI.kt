package com.example.framework.utils

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.View

class SystemUI {

    companion object{
        fun fixSystemUI(mActivity: Activity){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //获取最顶层的View
                mActivity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                mActivity.window.statusBarColor = Color.TRANSPARENT
            }
        }
    }
}