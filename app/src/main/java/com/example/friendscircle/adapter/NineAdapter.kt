package com.example.friendscircle.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.friendscircle.R
import com.example.friendscircle.`interface`.Adapter

class NineAdapter(private val data: MutableList<String>? = null, val context: Context) : Adapter {
    private val inflate by lazy {
        LayoutInflater.from(context)
    }

    override fun getView(container: ViewGroup, position: Int): View {
        val view = inflate.inflate(R.layout.item_nine_img, container, false)
        val imgView = view.findViewById<ImageView>(R.id.nine_img)
        Glide.with(context).load(data?.get(position)).placeholder(R.mipmap.connect_card_10)
            .centerCrop()
            .error(R.mipmap.connect_card_10).diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .into(imgView)
        return view
    }

    override fun getCount(): Int {
        return data?.size ?: 0
    }


}