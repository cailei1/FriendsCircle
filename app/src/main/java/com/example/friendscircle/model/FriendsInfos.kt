package com.example.friendscircle.model

class FriendsInfo : ArrayList<FriendsInfoItem>()

data class FriendsInfoItem(
    val comments: List<Comments>?,
    val content: String?,
    val error: String?,
    val images: List<Image>?,
    val sender: Sender?
)

data class Image(
    val url: String?
)

data class Sender(
    val avatar: String?,
    val nick: String?,
    val username: String?
)

data class Comments(
    val content: String?,
    val sender: Sender?
)