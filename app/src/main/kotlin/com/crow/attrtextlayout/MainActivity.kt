@file:Suppress("SameParameterValue")

package com.crow.attrtextlayout

import android.graphics.Color
import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.crow.attrtextlayout.databinding.ActivityMainBinding

@Suppress("SpellCheckingInspection")
class MainActivity : AppCompatActivity() {

    private val mBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val mContent = "好吧我觉i得有BUG-确定吗？？？？我觉得是肯定的！！qweiqx"
//    val mContent = "CROW/n自己/n自己/n写的一/n个静态/n文本组/n件，包含了静态文本/n布局 静态文/n本视图，手动计算文本位置 进行对应的绘制！代码量共计1300行左右，十分的简单！算是自定义View中的入门基础了！！/n！/n"
//    val mContent = "好吧我觉得有BUG-确定吗？？？？我觉得是肯定的！！qweiqx@%!xTIQNAQWENXOQWEM#&IA我阿斯顿维拉4i9992188nnaduqwuzxucqwbdq!@$@#@snajaiw"
//    val mContent = "好吧我觉得有BUG-确定吗？？？？我觉得是肯定的！！qweiqx@%!xTIQNAQWENXOQWEM#&IA"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onCreate()
        createAttrTextLayout(0f, 0f, 128, 64)
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
        val layout = BaseAttrTextLayout(this)
        layout.x = x
        layout.y = y
        mBinding.root.addView(layout)
        layout.layoutParams = FrameLayout.LayoutParams(width, height)
        layout.mGravity = BaseAttrTextLayout.GRAVITY_CENTER
        layout.mEnableSingleTextAnimation = true
        layout.mMultipleLineEnable = true
        layout.mResidenceTime = 3000
        layout.mFontSize = 14f
        layout.mAnimationMode = BaseAttrTextLayout.ANIMATION_ERASE_Y
        layout.mAnimationLeft = true
        layout.mAnimationTop = false
        layout.mFontMonoSpace = true
        layout.mGravity = BaseAttrTextLayout.GRAVITY_CENTER
        layout.mUpdateStrategy = BaseAttrTextLayout.STRATEGY_TEXT_UPDATE_LAZY
        layout.mAnimationStrategy = BaseAttrTextLayout.STRATEGY_ANIMATION_UPDATE_RESTART
        layout.mScrollSpeed = 14
        layout.mText = mContent
        return layout
    }
}