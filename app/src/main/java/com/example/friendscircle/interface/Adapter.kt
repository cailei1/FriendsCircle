package com.example.friendscircle.`interface`

import android.view.View
import android.view.ViewGroup

interface Adapter {

    fun getView(container: ViewGroup, position: Int): View

    fun getCount(): Int


}