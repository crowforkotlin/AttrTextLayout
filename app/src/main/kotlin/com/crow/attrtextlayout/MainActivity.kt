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
//        createAttrTextLayout(0f, 0f, 128, 64)
//        createAttrTextLayout(0f, 0f, 512, 256)
        val width = resources.displayMetrics.widthPixels / 2
        val height = resources.displayMetrics.heightPixels / 2
        lifecycleScope.launch {
            readFile()
            createAttrTextLayout(0f, 0f, width, height)
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

    private fun createAttrTextLayout(x: Float, y: Float, width: Int, height: Int): AttrTextLayout {
        val layout = AttrTextLayout(this)
//        layout.background = ContextCompat.getDrawable(this, android.R.color.white)
//        layout.x = x
////        layout.y = y
//        layout.mBackgroundColor = Color.MAGENTA
        val layoutParams = ConstraintLayout.LayoutParams(width, height)
        mBinding.root.addView(layout)
        layoutParams.startToStart = mBinding.root.id
        layoutParams.topToTop = mBinding.root.id
        layoutParams.endToEnd = mBinding.root.id
        layoutParams.bottomToBottom = mBinding.root.id
        layout.mUpdateStrategy = AttrTextLayout.STRATEGY_DIMENSION_DP_SP
        layout.mFontSize = 20f
        layout.layoutParams = layoutParams
        layout.mGravity = AttrTextLayout.GRAVITY_CENTER
        layout.mGradientDirection = null
        layout.mEnableSingleTextAnimation = true
        layout.mMultipleLineEnable = true
        layout.mResidenceTime = 1000
        layout.mAnimationMode = AttrTextLayout.ANIMATION_CONTINUATION_ERASE_X
        layout.mAnimationLeft = false
        layout.mAnimationTop = false
        layout.mFontMonoSpace = false
        layout.mFontBold = true
        layout.mFontFakeBold = true
        layout.mEnableAntiAlias = true
        layout.mUpdateStrategy = AttrTextLayout.STRATEGY_TEXT_UPDATE_DEFAULT
        layout.mAnimationStrategy = AttrTextLayout.STRATEGY_ANIMATION_UPDATE_DEFAULT
        layout.mMarginRow = 4f
        layout.mScrollSpeed = 13
        layout.mText = mContent
        return layout
    }
}