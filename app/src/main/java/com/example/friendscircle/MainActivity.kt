package com.example.friendscircle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.framework.base.BaseActivity
import com.example.framework.base.BaseUIActivity
import com.example.friendscircle.adapter.TweetsAdapter
import com.example.friendscircle.ext.no
import com.example.friendscircle.ext.otherwise
import com.example.friendscircle.ext.yes
import com.example.friendscircle.model.FriendsInfo
import com.example.friendscircle.model.FriendsInfoItem
import com.example.friendscircle.model.UserInfo
import com.example.friendscircle.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*
import java.util.Collections.addAll

class MainActivity : BaseUIActivity<MainPresenter>() {

    var friendInfo: FriendsInfo? = FriendsInfo()
    lateinit var tweetAdapter: TweetsAdapter
    fun successBackUserInfo(userInfo: UserInfo?) {
        Log.e("cailei", userInfo?.profileImage)
    }

    fun successBackFriendsInfo(friendsInfo: FriendsInfo?) {
        friendsInfo.isNullOrEmpty().no {
            this.friendInfo?.clear()
            this.friendInfo?.addAll(friendsInfo as Collection<FriendsInfoItem>)
            tweetAdapter.notifyDataSetChanged()
        }.otherwise {

        }
    }

    override fun initData() {
        presenter.getUserInfo()
        tweetAdapter = TweetsAdapter(friendInfo)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = tweetAdapter
        initToolbar()
    }

    private fun initToolbar() {
//        toolbar.setTitle("title")
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(false)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onResume() {
        super.onResume()
        presenter.getFriendTweets()
    }
}