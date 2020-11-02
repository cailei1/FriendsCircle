package com.example.friendscircle.widget

import android.content.Context
import android.graphics.Rect
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import com.example.friendscircle.`interface`.Adapter
import com.example.friendscircle.ext.dp

class NineImageLayout(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {
    private lateinit var adapter: Adapter
    private var itemSize = 0
    private var totalWidth = 0
    private var totalHeight = 0
    private var childRect: MutableList<Rect> = arrayListOf()
    private var childLeft = 0
    private var childTop = 0
    private var childRight = 0
    private var childBottom = 0
    private val space = 5.dp
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        measureChildren(widthMeasureSpec, heightMeasureSpec)
        when (childCount) {
            1 -> {
                totalWidth = getChildAt(0).measuredWidth
                totalHeight = getChildAt(0).measuredHeight
                childRect.add(Rect(0, 0, getChildAt(0).measuredWidth, getChildAt(0).measuredHeight))
            }
            2 -> {
                for (index in 0..childCount) {
                    totalWidth += getChildAt(index).measuredWidth
                    totalHeight = getChildAt(index).measuredHeight
                    childRect.add(Rect(childLeft, 0, totalWidth, totalHeight))
                    childLeft += (space.toInt() + getChildAt(index).measuredWidth)
                }
            }
            3 -> {
                for (index in 0 until childCount) {
                    totalWidth += (space.toInt() + getChildAt(index).measuredWidth)
                    totalHeight = getChildAt(index).measuredHeight
                    childRect.add(Rect(childLeft, 0, totalWidth, totalHeight))
                    childLeft += (space.toInt() + getChildAt(index).measuredWidth)
                }
            }
            4 -> {
                for (index in 0 until childCount) {
                    if (index < 2) {
                        totalWidth += getChildAt(index).measuredWidth
                        totalHeight = getChildAt(index).measuredHeight
                        childRect.add(Rect(childLeft, 0, totalWidth, totalHeight))
                        childLeft += (space.toInt() + getChildAt(index).measuredWidth)
                    } else {
                        val i = index % 2
                        val width = getChildAt(index).measuredWidth
                        childRect.add(Rect(i * width,
                            totalHeight,
                            (i + 1) * width,
                            2 * totalHeight))
                        childLeft += (space.toInt() + getChildAt(index).measuredWidth)
                    }
                }
                totalHeight = 2 * totalHeight

            }
            else -> {
                for (index in 0 until childCount) {
                    if (index < 3) {
                        totalWidth += (space.toInt() + getChildAt(index).measuredWidth)
                        totalHeight = getChildAt(index).measuredHeight
                        childRect.add(Rect(childLeft, 0, totalWidth, totalHeight))
                        childLeft += (space.toInt() + getChildAt(index).measuredWidth)
                    } else {
                        var h = index / 3
                        val i = index % 3
                        val width = getChildAt(index).measuredWidth
                        childRect.add(Rect(i * (width + space.toInt()),
                            h * (totalHeight + space.toInt()),
                            (i + 1) * (width + space.toInt()),
                            (h + 1) * (totalHeight + space.toInt())))
                        childLeft += (space.toInt() + getChildAt(index).measuredWidth)
                    }
                }
                totalHeight = 3 * totalHeight + 2 * space.toInt()
            }
        }
        setMeasuredDimension(totalWidth, totalHeight)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        for (index in 0 until itemSize) {
            getChildAt(index).layout(childRect[index].left,
                childRect[index].top,
                childRect[index].right,
                childRect[index].bottom)
        }
    }


    fun setNineAdapter(adapter: Adapter) {
        this.adapter = adapter
        itemSize = adapter.getCount()
        for (position in 0 until itemSize) {
            val view = adapter.getView(this, position)
            addView(view)
        }
        requestLayout()
    }


}