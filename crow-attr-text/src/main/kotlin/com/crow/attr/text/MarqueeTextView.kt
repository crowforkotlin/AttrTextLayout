package com.crow.attr.text

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.text.TextPaint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.TextView

/**
 * 跑马灯效果的TextView。
 *
 * @author luckchoudog
 */
class MarqueeTextView : View, Runnable {

    private var mTextX: Int = 0
    var mText: String = ""
    /**
     * 自定义的变量，记录当前滚动的位置。
     */
    private var currentScrollX = 0

    /**
     * 自定义的变量，是否停止跑动。
     */
    private var isStop = false

    /**
     * 自定义的变量，初始化的时候测量的字体的宽度值。
     */
    private var textWidth = 0

    /**
     * 自定义的变量，初始化的时候是否测量字体的宽度。
     */
    private var isMeasure = false

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    val paint = TextPaint().apply {
        color = Color.WHITE
        textSize = 14f
    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // 文字宽度只需获取一次就可以了
        Log.i("IAttrTextExt", "$scrollX")
        canvas.drawText(mText, mTextX.toFloat(), height / 2f, paint)
    }


    /**
     * 重写setText方法
     */
    fun setText(text: CharSequence) {
        // 重写setText 在setText的时候重新计算text的宽度
        mText = text.toString()
        this.isMeasure = false
    }

    override fun run() {
        // 滚动跨度，没执行一次这个文字平移的跨度。
        currentScrollX += 1
        if (isStop) {
            return
        }
        Log.i("IAttrTextExt", "$scrollX \t $width")
        if (scrollX <= -(this.width)) {
            currentScrollX = textWidth
        } else if (scrollX > width) {
            currentScrollX = 0
        }
        mTextX = currentScrollX
        invalidate()
        // 第二个参数可以控制平移的速度，数值越小表示越快，可以结合滚动跨度来设置平移速度。

        postDelayed(this, 16);
    }

    /**
     * 开始滚动
     */
    fun startScroll() {
        isStop = false
        this.removeCallbacks(this)
        post(this)
    }

    /**
     * 停止滚动
     */
    fun stopScroll() {
        isStop = true
    }

    /**
     * 从头开始滚动
     */
    fun startFor0() {
        currentScrollX = 0
        startScroll()
    }
}