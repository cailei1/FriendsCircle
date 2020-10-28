package com.example.framework.base

import android.os.Bundle
import com.example.framework.mvp.IMvpView
import com.example.framework.mvp.IPresenter

abstract class BasePresenter<out V : IMvpView<BasePresenter<V>>> : IPresenter<V> {
    override lateinit var view: @UnsafeVariance V
    override fun onCreate(saveInstanceState: Bundle?) = Unit

    override fun onSaveInstanceState(outState: Bundle) = Unit

    override fun onDestroy() = Unit

    override fun onStart() = Unit

    override fun onStop() = Unit

    override fun onResume() = Unit

    override fun onPause() = Unit


}