@file:Suppress("SameParameterValue")

package com.crow.attrtextlayout

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.crow.attrtextlayout.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Suppress("SpellCheckingInspection")
class MainActivity : AppCompatActivity() {

    private val mBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
//    private val mContent = "静态文本特效/n绘制MS级别/n文本支持多行/n支持多种颜色/n采用了协程框架n采用了协程框架n采用了协程框架"
    private val mContent = "文静g/n静g绘制MgS级别/n文本gY支持多行/n支持g多种颜色/n采用/n了协/n程Gg用了"
//    val mContent = "CROW/n自己/n自己/n写的一/n个静态/n文本组/n件，包含了静态文本/n布局 静态文/n本视图，手动计算文本位置 进行对应的绘制！代码量共计1300行左右，十分的简单！算是自定义View中的入门基础了！！/n！/n"
//    val mContent = "好吧我觉得有BUG-确定吗？？？？我觉得是肯定的！！qweiqx@%!xTIQNAQWENXOQWEM#&IA我阿斯顿维拉4i9992188nnaduqwuzxucqwbdq!@$@#@snajaiw"
//    val mContent = "好吧我觉得有BUG-确定吗？？？？我觉得是肯定的！！qweiqx@%!xTIQNAQWENXOQWEM#&IA"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onCreate()
        requestedOrientation = android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        createAttrTextLayout(0f, 0f, 128, 64)
//        createAttrTextLayout(0f, 0f, 512, 256)
//        createAttrTextLayout(0f, 0f, MATCH_PARENT, MATCH_PARENT)
    }

    private fun onCreate() {
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(Color.WHITE, Color.WHITE),
            navigationBarStyle = SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT)
        )
        setContentView(mBinding.root)
        WindowCompat.getInsetsController(window, mBinding.root).isAppearanceLightStatusBars = false
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun createAttrTextLayout(x: Float, y: Float, width: Int, height: Int): BaseAttrTextLayout {
        val textSizeInPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 13.6f, resources.displayMetrics) // 将文本大小从 sp 转换
//        val textSizeInPx = 14f
        val layout = BaseAttrTextLayout(this)
//        layout.background = ContextCompat.getDrawable(this, android.R.color.white)
        layout.x = x
        layout.y = y
        layout.mFontSize = textSizeInPx
        mBinding.root.addView(layout)
        layout.layoutParams = FrameLayout.LayoutParams(width, height)
        layout.mGravity = BaseAttrTextLayout.GRAVITY_CENTER
        layout.mEnableSingleTextAnimation = true
        layout.mMultipleLineEnable = true
        layout.mResidenceTime = 1000
        layout.mAnimationMode = BaseAttrTextLayout.ANIMATION_CONTINUATION_ERASE_X
        layout.mAnimationLeft = true
        layout.mAnimationTop = true
        layout.mFontMonoSpace = true
        layout.mUpdateStrategy = BaseAttrTextLayout.STRATEGY_TEXT_UPDATE_LAZY
        layout.mAnimationStrategy = BaseAttrTextLayout.STRATEGY_ANIMATION_UPDATE_RESTART
        layout.mScrollSpeed = 15
        layout.mMarginRow = 0f
        layout.mText = mContent
        return layout
    }
}