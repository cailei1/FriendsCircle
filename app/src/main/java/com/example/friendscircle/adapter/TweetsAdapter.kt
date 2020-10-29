package com.example.friendscircle.adapter

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.friendscircle.R
import com.example.friendscircle.model.FriendsInfo
import com.example.friendscircle.model.FriendsInfoItem

class TweetsAdapter(data: FriendsInfo?) :
    BaseQuickAdapter<FriendsInfoItem, BaseViewHolder>(R.layout.item_tweets, data) {


    override fun convert(holder: BaseViewHolder, item: FriendsInfoItem) {
        holder.setText(R.id.tv_friend_name, item.sender?.username)
        holder.setText(R.id.tv_friend_content, item.content)
        val avatarView: ImageView = holder.getView(R.id.avatar)
        Glide.with(context).load(item.sender?.avatar).into(avatarView)
    }
}