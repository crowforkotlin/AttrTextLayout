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
import androidx.lifecycle.lifecycleScope
import com.crow.attr.text.AttrTextLayout
import com.crow.attrtextlayout.databinding.ActivityMainBinding
import com.crow.base.tools.extensions.copyFolder
import kotlinx.coroutines.Dispatchers
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
        requestedOrientation = android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
//        createAttrTextLayout(0f, 0f, 512, 256)
//        val width = resources.displayMetrics.widthPixels / 2
//        val height = resources.displayMetrics.heightPixels / 2
        AttrTextLayout.mAwaitAnimationCount = 1
        lifecycleScope.launch {
            readFile()
            /*.also { layout ->
                val text = layout.mText + "456"
                lifecycleScope.launch {
                    repeat(Int.MAX_VALUE) {
                        delay(2000)
                        layout.mText = text
                    }
                }*/
            repeat(4) {
                // createAttrTextLayout(0f, it * 16f, 128, 16, AttrTextLayout.ANIMATION_MOVE_X_DRAW)
//                createAttrTextLayout(0f, 16f, 128, 16, AttrTextLayout.ANIMATION_MOVE_X_DRAW)
//                createAttrTextLayout(0f, 32f, 128, 16, AttrTextLayout.ANIMATION_MOVE_X_DRAW)
//                createAttrTextLayout(0f, 48f, 128, 16, AttrTextLayout.ANIMATION_MOVE_X_DRAW)
            }
//            createAttrTextLayout(0f, 32f, 128, 32, AttrTextLayout.ANIMATION_MOVE_X_DRAW)
//            createAttrTextLayout(0f, 0f, width, height)
        }
    }

    private suspend fun readFile() {
        withContext(Dispatchers.IO) {
            copyFolder("content")
            mContent = File(filesDir, "content/Content.txt").readText()
        }
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

    private fun createAttrTextLayout(x: Float, y: Float, width: Int, height: Int, animationStrategy: Short): AttrTextLayout {
        val layout = AttrTextLayout(this)
//        layout.background = ContextCompat.getDrawable(this, android.R.color.white)
        layout.x = x
        layout.y = y
        val layoutParams = FrameLayout.LayoutParams(width, height)
        mBinding.root.addView(layout)
        layout.layoutParams = layoutParams
        layout.mTextSize = 14f
        layout.mTextGravity = AttrTextLayout.GRAVITY_CENTER
        layout.mTextGradientDirection = AttrTextLayout.GRADIENT_VERTICAL
        layout.mSingleTextAnimationEnable = true
        layout.mTextMultipleLineEnable = false
        layout.mTextResidenceTime = 3000
        layout.mTextAnimationMode = animationStrategy
        layout.mTextAnimationLeftEnable = false
        layout.mTextAnimationTopEnable = false
        layout.mTextMonoSpaceEnable = false
        layout.mTextBoldEnable = false
        layout.mTextFakeBoldEnable = false
        layout.mTextAntiAliasEnable = false
        layout.mTextItalicEnable = false
        layout.mTextSizeUnitStrategy
        layout.mTextGradientDirection = AttrTextLayout.GRADIENT_BEVEL
        layout.mTextUpdateStrategy = AttrTextLayout.STRATEGY_TEXT_UPDATE_ALL
        layout.mTextAnimationStrategy = AttrTextLayout.STRATEGY_ANIMATION_UPDATE_CONTINUA
        layout.mTextRowMargin = 4f
        layout.mTextCharSpacing = 1f
        layout.mTextScrollSpeed = 13
        layout.mText = mContent
        return layout
    }
}