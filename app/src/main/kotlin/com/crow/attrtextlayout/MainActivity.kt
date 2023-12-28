@file:Suppress("SameParameterValue")

package com.crow.attrtextlayout

import android.graphics.Color
import android.os.Bundle
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.crow.attr.text.AttrTextLayout
import com.crow.attrtextlayout.databinding.ActivityMainBinding

@Suppress("SpellCheckingInspection")
class MainActivity : AppCompatActivity() {

    private val mBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val mContent = "属性文本特效/n绘制MS级别/n文本支持多行/n支持多种颜色/n采用了协程框架/n文本支持实时更新/n绘制时间控制在<3ms"
//    val mContent = "好吧我觉得有BUG-确定吗？？？？我觉得是肯定的！！qweiqx@%!xTIQNAQWENXOQWEM#&IA我阿斯顿维拉4i9992188nnaduqwuzxucqwbdq!@$@#@snajaiw"
//    val mContent = "好吧我觉得有BUG-确定吗？？？？我觉得是肯定的！！qweiqx@%!xTIQNAQWENXOQWEM#&IA"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onCreate()
        requestedOrientation = android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
//        createAttrTextLayout(0f, 0f, 128, 64)
//        createAttrTextLayout(0f, 0f, 512, 256)
        val width = resources.displayMetrics.widthPixels / 2
        val height = resources.displayMetrics.heightPixels / 2
        createAttrTextLayout(0f, 0f, width, height)
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

    private fun createAttrTextLayout(x: Float, y: Float, width: Int, height: Int): AttrTextLayout {
//        val textSizeInPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 180f, resources.displayMetrics) // 将文本大小从 sp 转换
        val textSizeInPx = 28f
        val layout = AttrTextLayout(this)
//        layout.background = ContextCompat.getDrawable(this, android.R.color.white)
//        layout.x = x
//        layout.y = y
        val layoutParams = ConstraintLayout.LayoutParams(width, height)
        mBinding.root.addView(layout)
        layoutParams.startToStart = mBinding.root.id
        layoutParams.topToTop = mBinding.root.id
        layoutParams.endToEnd = mBinding.root.id
        layoutParams.bottomToBottom = mBinding.root.id
        layout.mUpdateStrategy = AttrTextLayout.STRATEGY_DIMENSION_DP_SP
        layout.mFontSize = textSizeInPx
        layout.layoutParams = layoutParams
        layout.mGravity = AttrTextLayout.GRAVITY_CENTER
        layout.mEnableSingleTextAnimation = true
        layout.mMultipleLineEnable = true
        layout.mResidenceTime = 1000
        layout.mAnimationMode = AttrTextLayout.ANIMATION_CENTER
        layout.mAnimationLeft = false
        layout.mAnimationTop = false
        layout.mFontMonoSpace = true
        layout.mUpdateStrategy = AttrTextLayout.STRATEGY_TEXT_UPDATE_DEFAULT
        layout.mAnimationStrategy = AttrTextLayout.STRATEGY_ANIMATION_UPDATE_DEFAULT
        layout.mScrollSpeed = 13
        layout.mMarginRow = 13.1835f
        /*lifecycleScope.launch {
            repeat(Int.MAX_VALUE) {
//                layout.mFontSpacing = (1..10).random().toFloat()
                delay(3000)
                layout.mText = "$it"
            }
        }*/
        layout.mText = mContent
        return layout
    }
}