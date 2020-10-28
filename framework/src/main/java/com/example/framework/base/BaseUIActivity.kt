package com.example.framework.base

import android.os.Bundle
import com.example.framework.mvp.IPresenter
import com.example.framework.utils.SystemUI

abstract class BaseUIActivity<out P : BasePresenter<BaseActivity<P>>> : BaseActivity<P>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SystemUI.fixSystemUI(this)
    }
}