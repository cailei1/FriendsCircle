package com.example.framework.mvp

import android.os.Bundle

interface ILifecycle {
    fun onCreate(saveInstanceState:Bundle?)

    fun onSaveInstanceState(outState: Bundle)

    fun onDestroy()

    fun onStart()

    fun onStop()

    fun onResume()

    fun onPause()
}