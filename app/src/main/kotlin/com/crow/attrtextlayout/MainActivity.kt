@file:Suppress("SameParameterValue")

package com.crow.attrtextlayout

import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.crow.attr.text.AttrTextFrameConfig
import com.crow.attr.text.AttrTextLayout
import com.crow.attrtextlayout.databinding.ActivityMainBinding
import com.crow.base.tools.extensions.copyFolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

@Suppress("SpellCheckingInspection")
class MainActivity : AppCompatActivity() {

    private val mBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private var mContent: String = ""
//    val mContent = "好吧我觉得有BUG-确定吗？？？？我觉得是肯定的！！qweiqx@%!xTIQNAQWENXOQWEM#&IA我阿斯顿维拉4i9992188nnaduqwuzxucqwbdq!@$@#@snajaiw"
//    val mContent = "好吧我觉得有BUG-确定吗？？？？我觉得是肯定的！！qweiqx@%!xTIQNAQWENXOQWEM#&IA"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onCreate()
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                copyFolder("content")
                copyFolder("font")
                mContent = File(filesDir, "content/Content.txt").readText()
            }
//            mBinding.attrTextLayout.mText = mContent
            /*withContext(Dispatchers.IO) {
                repeat(Int.MAX_VALUE) {
                    mBinding.attrTextLayout.mText = "$it $mContent"
                    delay(1000)
                }
            }*/
            /*repeat(10) {
               delay((100..700).random().toLong())
                createAttrTextLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, AttrTextLayout.ANIMATION_MOVE_X_HIGH_BRUSH_DRAW)
            }*/
            /*repeat(Int.MAX_VALUE) {
                delay(500)
                mBinding.attrTextLayout.mTextFrameConfig = AttrTextFrameConfig(
                    mLeft = true,
                    mTop = true,
                    mRight = true,
                    mBottom = true, mLineWidth = (20..50).random().toFloat())
            }*/
            createAttrTextLayout(128, FrameLayout.LayoutParams.WRAP_CONTENT, AttrTextLayout.ANIMATION_CONTINUATION_ERASE_X)
        }
    }

    private fun onCreate() {
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(Color.WHITE, Color.WHITE),
            navigationBarStyle = SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT)
        )
        setContentView(mBinding.root)
        WindowCompat.getInsetsController(window, mBinding.root).apply {
            isAppearanceLightStatusBars = false
            hide(WindowInsetsCompat.Type.systemBars())
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        requestedOrientation = android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }

    private fun createAttrTextLayout(width: Int, height: Int, animationStrategy: Short): AttrTextLayout {
        val layout = AttrTextLayout(this)
        val layoutParams = LinearLayout.LayoutParams(width, height)
        mBinding.linear.addView(layout)
        layout.layoutParams = layoutParams
        layout.mTextSize = 14f
        layout.mTextGravity = AttrTextLayout.GRAVITY_CENTER
        layout.mTextGradientDirection = AttrTextLayout.GRADIENT_VERTICAL
        layout.mTextSizeUnitStrategy = AttrTextLayout.STRATEGY_DIMENSION_PX_OR_DEFAULT
        layout.mSingleTextAnimationEnable = true
        layout.mTextMultipleLineEnable = true
        layout.mTextResidenceTime = 1000
        layout.mTextAnimationMode = animationStrategy
        layout.mTextAnimationLeftEnable = false
        layout.mTextAnimationTopEnable = false
        layout.mTextLines = 3
        layout.mTextMonoSpaceEnable = false
        layout.mTextBoldEnable = false
        layout.mTextFakeBoldEnable = false
        layout.mTextFakeItalicEnable = false
        layout.mTextAntiAliasEnable = false
        layout.mTextItalicEnable = false
        layout.mTextSizeUnitStrategy
        layout.mTextGradientDirection = AttrTextLayout.GRADIENT_BEVEL
        layout.mTextUpdateStrategy = AttrTextLayout.STRATEGY_TEXT_UPDATE_ALL
        layout.mTextAnimationStrategy = AttrTextLayout.STRATEGY_ANIMATION_UPDATE_CONTINUA
        layout.mTextRowMargin = 4f
        layout.mTextCharSpacing = 1f
        layout.mTextAnimationSpeed = 15
        layout.mTextFrameConfig = AttrTextFrameConfig(mLeft = true, mTop = true, mRight = true, mBottom = true, mGradient = AttrTextLayout.GRADIENT_BEVEL)
        layout.mText = mContent
        return layout
    }
}