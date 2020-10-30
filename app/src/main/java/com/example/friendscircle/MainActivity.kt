package com.example.friendscircle

import android.graphics.Color
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
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.internal.and
import java.util.Collections.addAll
import kotlin.math.abs

class MainActivity : BaseUIActivity<MainPresenter>() {

    private var friendInfo: FriendsInfo? = FriendsInfo()
    private lateinit var tweetAdapter: TweetsAdapter
    private var toolbarHeight: Int = 0

    private val COLOR_WHITE = Color.WHITE
    private val COLOR_BLACK = Color.BLACK

    private var percent: Float = 0f
    val a = (COLOR_BLACK.and(0xff000000).shr(24))

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
        setAppbarListener()

    }

    private fun setAppbarListener() {
        appBar.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            override fun onOffsetChanged(appBarLayout: AppBarLayout?, y: Int) {
                var appBarHeight = appBarLayout?.totalScrollRange
//                Log.e("appBarY", "y : $y  ,  appBarHeight : $appBarHeight")
                var distance = appBarHeight?.minus(abs(y))
                if (distance != null) {
                    if (distance == appBarHeight || distance > toolbarHeight) {
                        setToolBarColor(Color.WHITE)
                    } else if (distance < toolbarHeight) {
                        percent = ((toolbarHeight.minus(distance).toFloat()).div(toolbarHeight))
                        Log.e(
                            "appBarY",
                            "toolbarHeight $toolbarHeight distance : ${((toolbarHeight.minus(
                                distance
                            )).div(toolbarHeight))} percent : $percent alpha : ${a}"
                        )
                        setToolBarColor(
                            Color.argb(
                                (a * percent.toFloat()).toInt(),
                                COLOR_BLACK.and(0x00ff0000).shr(16),
                                COLOR_BLACK.and(0x0000ff00).shr(8),
                                COLOR_BLACK.and(0x000000ff)
                            )
                        )
                    }
                }
            }

        })
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setToolBarColor(Color.WHITE)
        getToolBarHeight()
    }

    private fun getToolBarHeight() {
        toolbar.post {
            toolbarHeight = toolbar.height / 2
        }
    }

    private fun setToolBarColor(colorId: Int) {
        val backDrawable = toolbar.navigationIcon
        backDrawable?.mutate()
        backDrawable?.setTint(colorId)
        val cameraDrawable = iv_camera.drawable
        cameraDrawable?.mutate()
        cameraDrawable?.setTint(colorId)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onResume() {
        super.onResume()
        presenter.getFriendTweets()
    }
}