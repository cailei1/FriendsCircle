package com.example.friendscircle.adapter

import android.util.Log
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.friendscircle.R
import com.example.friendscircle.model.FriendsInfo
import com.example.friendscircle.model.FriendsInfoItem
import com.example.friendscircle.widget.NineImageLayout
import kotlinx.android.synthetic.main.item_tweets.*

class TweetsAdapter(data: FriendsInfo?) :
    BaseQuickAdapter<FriendsInfoItem, BaseViewHolder>(R.layout.item_tweets, data) {


    override fun convert(holder: BaseViewHolder, item: FriendsInfoItem) {
        if (item.sender != null) {
            holder.setText(R.id.tv_friend_name, item.sender?.nick)
            holder.setText(R.id.tv_friend_content, item.content)
            val avatarView: ImageView = holder.getView(R.id.avatar)
            Log.e("cailei sender avatar", item.sender?.avatar)
            Glide.with(context).load(item.sender?.avatar)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(10)))
                .centerCrop()
                .load(item.sender.avatar)
                .placeholder(R.mipmap.default_nor_avatar)
                .error(R.mipmap.default_nor_avatar)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(avatarView)
            val nineLayout = holder.getView<NineImageLayout>(R.id.nine_layout)
            val datas =
                mutableListOf<String>("http://gank.io/images/f4f6d68bf30147e1bdd4ddbc6ad7c2a2",
                    "http://gank.io/images/f4f6d68bf30147e1bdd4ddbc6ad7c2a2",
                    "http://gank.io/images/f4f6d68bf30147e1bdd4ddbc6ad7c2a2",
                    "http://gank.io/images/f4f6d68bf30147e1bdd4ddbc6ad7c2a2",
                    "http://gank.io/images/f4f6d68bf30147e1bdd4ddbc6ad7c2a2",
                    "https://img.xjh.me/mobile/bg/nature/63792823_p0.jpg",
                    "https://img.xjh.me/mobile/bg/nature/63792823_p0.jpg", "https://img.xjh.me/mobile/bg/nature/63792823_p0.jpg"
                )
            val nineAdapter = NineAdapter(datas, context)
            nineLayout.setNineAdapter(nineAdapter)
        }
    }
}