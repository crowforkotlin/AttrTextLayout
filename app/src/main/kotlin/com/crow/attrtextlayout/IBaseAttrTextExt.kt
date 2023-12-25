@file:Suppress("SpellCheckingInspection", "unused", "DEPRECATION")

package com.crow.attrtextlayout

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Region
import android.os.Build
import kotlin.math.sqrt

interface IBaseAttrTextExt {

    companion object {

        /**
         * ● 调试模式
         *
         * ● 2023-12-22 15:13:15 周五 下午
         * @author crowforkotlin
         */
        const val DEBUG = true
        const val DEBUG_TEXT = true
        const val DEBUG_ANIMATION = true
    }

    var mAnimationTop: Boolean
    var mAnimationLeft: Boolean
    var mAnimationMode: Short
    var mMarginRow: Float

    /**
     * ● 获取文本高度：ascent绝对值 + descent
     *
     * ● 2023-11-29 17:01:15 周三 下午
     * @author crowforkotlin
     */
    fun getTextHeight(fontMetrics: Paint.FontMetrics) : Float {
        return fontMetrics.descent -fontMetrics.ascent
    }

    /**
     * ● 绘制菱形
     *
     * ● 2023-12-25 15:19:02 周一 下午
     * @author crowforkotlin
     */
    fun Canvas.drawRhombus(path: Path, width: Int, height: Int, fraction: Float) {
        val halfWidth = width shr 1
        val halfHeight = height shr 1
        val halfWidthFloat = halfWidth.toFloat()
        val halfHeightFloat = halfHeight.toFloat()
        val xRate = width * fraction
        val yRate = height * fraction
        path.moveTo(halfWidthFloat, -halfHeight + yRate)
        path.lineTo(-halfWidth + xRate, halfHeightFloat)
        path.lineTo(halfWidthFloat, height + halfHeight - yRate)
        path.lineTo(width + halfWidth - xRate, halfHeightFloat)
        if (DEBUG_ANIMATION) {
            val paint = Paint().apply { color = Color.YELLOW; strokeWidth = 2f }
            drawLine(halfWidthFloat, -halfHeight + yRate, halfWidthFloat + halfWidthFloat, (-halfHeight + yRate) + (-halfHeight + yRate), paint)
            drawLine(-halfWidth + xRate, halfHeightFloat, (-halfWidth + xRate) + (-halfWidth + xRate), halfHeightFloat + halfHeightFloat, paint)
            drawLine(halfWidthFloat, height + halfHeight - yRate, halfWidthFloat + halfWidthFloat, (height + halfHeight - yRate) + (height + halfHeight - yRate), paint)
            drawLine(width + halfWidth - xRate, halfHeightFloat, (width + halfWidth - xRate) + (width + halfWidth - xRate), halfHeightFloat + halfHeightFloat, paint)
        }
    }

    /**
     * ● 绘制圆形 时钟动画
     *
     * ● 2023-12-25 15:22:48 周一 下午
     * @author crowforkotlin
     */
    fun Canvas.drawOval(path: Path, width: Int, height: Int, fraction: Float) {
        val widthFloat = width.toFloat()
        val heightFloat = height.toFloat()
        val diagonal = sqrt(widthFloat * widthFloat + heightFloat * heightFloat)
        val widthHalf = widthFloat / 2f
        val heightHalf = heightFloat / 2f
        path.addArc(widthHalf - diagonal, heightHalf - diagonal, widthFloat + diagonal - widthHalf, heightFloat + diagonal -heightHalf,270f,360 * fraction)
        path.lineTo(widthHalf,heightHalf)
        if (DEBUG_ANIMATION) {
            val paint = Paint().apply { color = Color.BLUE; strokeWidth = 2f }
            drawLine(widthHalf - diagonal, heightHalf - diagonal, width + diagonal - widthHalf, height + diagonal - heightHalf, paint)
            drawLine(0f, 0f, widthHalf, heightHalf, paint)
        }
    }

    /**
     * ● 绘制十字扩展 动画
     *
     * ● 2023-12-25 15:23:15 周一 下午
     * @author crowforkotlin
     */
    fun Canvas.drawCrossExtension(width: Int, height: Int, fraction: Float) {
        val rectXRate = (width shr 1) * fraction
        val rectYRate = (height shr 1) * fraction
        val widthFloat = width.toFloat()
        val heightFloat = height.toFloat()
        drawCrossExtension(rectXRate, rectYRate, widthFloat, heightFloat)
    }

    /**
     * ● 绘制十字扩展 动画
     *
     * ● 2023-12-25 15:23:15 周一 下午
     * @author crowforkotlin
     */
    fun Canvas.drawCrossExtension(rectXRate: Float, rectYRate: Float, widthFloat: Float, heightFloat: Float) {
        withApiO(
            leastO = {
                clipOutRect(0f,  rectYRate, widthFloat, heightFloat - rectYRate) // 上下
                clipOutRect(rectXRate, 0f, widthFloat - rectXRate, heightFloat)  // 左右
                if (DEBUG_ANIMATION) {
                    val paint = Paint().apply { color = Color.BLUE; strokeWidth = 2f }
                    drawLine(0f,  rectYRate, widthFloat, heightFloat - rectYRate, paint) // 上下
                    drawLine(rectXRate, 0f, widthFloat - rectXRate, heightFloat, paint)  // 左右
                }
            },
            lessO = {
                clipRect(0f,  rectYRate, widthFloat, heightFloat - rectYRate, Region.Op.DIFFERENCE) // 上下
                clipRect(rectXRate, 0f, widthFloat - rectXRate, heightFloat, Region.Op.DIFFERENCE)  // 左右
                if (DEBUG_ANIMATION) {
                    val paint = Paint().apply { color = Color.BLUE; strokeWidth = 2f }
                    drawLine(0f,  rectYRate, widthFloat, heightFloat - rectYRate, paint) // 上下
                    drawLine(rectXRate, 0f, widthFloat - rectXRate, heightFloat, paint)  // 左右
                }
            }
        )
    }

    /**
     * ● 绘制同方向 反效果的十字扩展 动画
     *
     * ● 2023-12-25 15:23:52 周一 下午
     * @author crowforkotlin
     */
    fun Canvas.drawDifferenceCrossExtension(rectXRate: Float, rectYRate: Float, widthFloat: Float, heightFloat: Float) {
        clipRect(0f,  rectYRate, widthFloat, height - rectYRate) // 上下
        clipRect(rectXRate, 0f, width - rectXRate, height.toFloat())  // 左右
        if (DEBUG_ANIMATION) {
            val paint = Paint().apply { color = Color.YELLOW; strokeWidth = 2f }
            drawLine(0f,  rectYRate, widthFloat, height - rectYRate, paint)
            drawLine(rectXRate, 0f, width - rectXRate, height.toFloat(), paint)
        }
    }

    /**
     * ● 绘制擦除Y轴方向的动画
     *
     * ● 2023-12-25 15:24:31 周一 下午
     * @author crowforkotlin
     */
    fun Canvas.drawEraseY(widthFloat: Float, heightFloat: Float, yRate: Float) {
        drawY(
            onTop = {
                clipRect(0f, heightFloat - yRate, widthFloat, heightFloat)
                if(DEBUG_ANIMATION) {
                    val paint = Paint().apply { color = Color.BLUE; strokeWidth = 2f }
                    drawLine(0f, heightFloat - yRate, widthFloat, heightFloat, paint)
                }
            },
            onBottom = {
                clipRect(0f, 0f, widthFloat, yRate)
                if(DEBUG_ANIMATION) {
                    val paint = Paint().apply { color = Color.YELLOW; strokeWidth = 2f }
                    drawLine(0f, 0f, widthFloat, yRate, paint)
                }
            }
        )
    }

    /**
     * ● 绘制同方向 反效果的擦除Y轴方向的动画
     *
     * ● 2023-12-25 15:24:43 周一 下午
     * @author crowforkotlin
     */
    fun Canvas.drawDifferenceEraseY(widthFloat: Float, heightFloat: Float, yRate: Float) {
        drawY(
            onTop = {
                clipRect(0f, 0f, widthFloat, heightFloat - yRate)
                if(DEBUG_ANIMATION) {
                    val paint = Paint().apply { color = Color.YELLOW; strokeWidth = 2f }
                    drawLine(0f, 0f, widthFloat, heightFloat - yRate, paint)
                }
            },
            onBottom = {
                clipRect(0f, yRate, widthFloat, heightFloat)
                if(DEBUG_ANIMATION) {
                    val paint = Paint().apply { color = Color.BLUE; strokeWidth = 4f }
                    drawLine(0f, yRate, widthFloat, heightFloat, paint)
                }
            }
        )
    }

    /**
     * ● 绘制擦除X轴方向的动画
     *
     * ● 2023-12-25 15:25:01 周一 下午
     * @author crowforkotlin
     */
    fun Canvas.drawEraseX(widthFloat: Float, heightFloat: Float, xRate: Float) {
        drawX(
            onLeft = {
                clipRect(widthFloat - xRate, 0f, widthFloat, heightFloat)
                if(DEBUG_ANIMATION) {
                    val paint = Paint().apply { color = Color.BLUE; strokeWidth = 2f }
                    drawLine(widthFloat - xRate, 0f, widthFloat, heightFloat, paint)
                }
            },
            onRight = {
                if(DEBUG_ANIMATION) {
                    val paint = Paint().apply { color = Color.YELLOW; strokeWidth = 2f; }
                    drawLine(0f, 0f, xRate, heightFloat, paint)
                }
                clipRect(0f, 0f, xRate, heightFloat)
            }
        )
    }

    /**
     * ● 绘制同方向 反效果的擦除X轴方向的动画
     *
     * ● 2023-12-25 15:25:48 周一 下午
     * @author crowforkotlin
     */
    fun Canvas.drawDifferenceEraseX(widthFloat: Float, heightFloat: Float, xRate: Float) {
        drawX(
            onLeft = {
                clipRect(0f, 0f, widthFloat - xRate, heightFloat)
                if(DEBUG_ANIMATION) {
                    val paint = Paint().apply { color = Color.YELLOW; strokeWidth = 2f }
                    drawLine(0f, 0f, widthFloat - xRate, heightFloat, paint)
                }
            },
            onRight = {
                clipRect(xRate, 0f, widthFloat, heightFloat)
                if(DEBUG_ANIMATION) {
                    val paint = Paint().apply { color = Color.BLUE; strokeWidth = 2f }
                    drawLine(xRate, 0f, widthFloat, heightFloat, paint)
                }
            }
        )
    }
}

inline fun IBaseAttrTextExt.drawY(onTop: () -> Unit, onBottom: () -> Unit) {
    if (mAnimationTop) onTop() else onBottom()
}

inline fun IBaseAttrTextExt.drawX(onLeft: () -> Unit, onRight: () -> Unit) {
    if (mAnimationLeft) onLeft() else onRight()
}

inline fun debug(onDebug: () -> Unit) {
    if (IBaseAttrTextExt.DEBUG) onDebug()
}

inline fun debugText(onDebug: () -> Unit, orElse: () -> Unit) {
    if (IBaseAttrTextExt.DEBUG_TEXT) onDebug() else orElse()
}

inline fun debugAnimation(onDebug: () -> Unit) {
    if (IBaseAttrTextExt.DEBUG_ANIMATION) onDebug()
}

inline fun withPath(path:Path, pathOperations: Path.() -> Unit) {
    path.reset()
    path.pathOperations()
    path.close()
}

inline fun withApiO(leastO: () -> Unit, lessO: () -> Unit) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) leastO() else lessO()
}