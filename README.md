# AttrTextLayout

![Kotlin Support Verison](https://img.shields.io/badge/Kotlin_Version-1.3.0+-blue) ![Android Support Version](https://img.shields.io/badge/Android_Version-4.4+-blue) ![Compat](https://img.shields.io/badge/Compat-AndroidX_&_Support_Library-blue)

```kotlin

// 添加MavenCentral源
repository { mavenCentral() }

// 引入远程依赖
implementation("com.kotlincrow.android.component:AttrTextLayout:1.0")
```

- [x] 配置文本策略、样式、换行、特效(擦出、移动、始终、连续动画、非连续动画)
- [x] 优化绘制速度 < 3MS
- [x] 增加渐变色


<table>
	<tr>
		<td align="center" style="padding: 10px;"><img src="docs/img/Continuation-EraseXLeft.gif"></td>
		<td align="center" style="padding: 10px;"><img src="docs/img/Continuation-EraseYTop.gif"></td>
	</tr>
    <tr>
		<td align="center" style="padding: 10px;">Continuation-EraseX - X轴向左连续擦除</td>
		<td align="center" style="padding: 10px;">Continuation-EraseYTop - Y轴向上连续擦除</td>
	</tr>
</table>

<table>
	<tr>
		<td align="center" style="padding: 10px;"><img src="docs/img/Continuation-Oval.gif"></td>
		<td align="center" style="padding: 10px;"><img src="docs/img/Continuation-Rhombus.gif"></td>
	</tr>
    <tr>
		<td align="center" style="padding: 10px;">Continuation-Oval - 连续圆形时钟</td>
		<td align="center" style="padding: 10px;">Continuation-Rhombus - 连续菱形</td>
	</tr>
</table>

<table>
	<tr>
		<td align="center" style="padding: 10px;"><img src="docs/img/Continuation-Cross.gif"></td>
		<td align="center" style="padding: 10px;"><img src="docs/img/Center.gif"></td>
	</tr>
    <tr>
		<td align="center" style="padding: 10px;">Continuation-Oval - 连续十字扩展</td>
		<td align="center" style="padding: 10px;">Center - 中心缩放</td>
	</tr>
</table>

<table>
	<tr>
		<td align="center" style="padding: 10px;"><img src="docs/img/Move_XRight.gif"></td>
		<td align="center" style="padding: 10px;"><img src="docs/img/Move_YBottom.gif"></td>
	</tr>
    <tr>
		<td align="center" style="padding: 10px;">Move Right - X轴向右移动</td>
		<td align="center" style="padding: 10px;">Move Bottom - Y轴向下移动</td>
	</tr>
</table>

<table>
	<tr>
		<td align="center" style="padding: 10px;"><img src="docs/img/Fade.gif"></td>
		<td align="center" style="padding: 10px;"><img src="docs/img/Fade_Sync.gif"></td>
	</tr>
    <tr>
		<td align="center" style="padding: 10px;">Fade - 淡入淡出_异步</td>
		<td align="center" style="padding: 10px;">Fade-Sync - 淡入淡出_同步</td>
	</tr>
</table>

---

```kotlin
// 创建AttrTextLayout对象
val layout = AttrTextLayout(this)

// 设置更新策略为DP和SP 接下来您指定的字体大小 文本间距都会自动以DP SP处理，而不是像素
layout.mUpdateStrategy = AttrTextLayout.STRATEGY_DIMENSION_DP_SP

// 设置动画模式为连续X轴擦除 
layout.mAnimationMode = AttrTextLayout.ANIMATION_CONTINUATION_ERASE_X

// 设置默认文本更新策略 不设置也是默认
layout.mUpdateStrategy = AttrTextLayout.STRATEGY_TEXT_UPDATE_DEFAULT

// 设置默认动画更新策略 不设置也是默认
layout.mAnimationStrategy = AttrTextLayout.STRATEGY_ANIMATION_UPDATE_DEFAULT

// 设置文本居中
layout.mGravity = AttrTextLayout.GRAVITY_CENTER

// 启用单行文本动画
layout.mEnableSingleTextAnimation = true

// 支持多行文本
layout.mMultipleLineEnable = true

// 设置X轴的动画向左 false 为右
layout.mAnimationLeft = true

// 设置Y轴的动画向上 false 为下
layout.mAnimationTop = true

// 使用等宽字体 确保动态刷新时不会出现跳动
layout.mFontMonoSpace = true

// 设置停留时间
layout.mResidenceTime = 1000

// 设置字体大小
layout.mFontSize = 28f

// 设置滚动速度 （1 - 15）
layout.mScrollSpeed = 13

// 设置行间距
layout.mMarginRow = 10f

// 设置文本（设置后会自动更新，前提你得吧这个layout添加到您的视图里面，直到您添加完成mText也会自动生效，除非不设置）
layout.mText = "Hello World!"
```

```kotlin 策略类别
/**
 * ● 默认更新策略：当文本发生改变触发绘制需求时会直接更新绘制视图
 */
const val STRATEGY_TEXT_UPDATE_DEFAULT: Short = 600

/**
 * ● 懒加载更新策略：当文本发生改变时 并不会触发绘制视图的需求 只有下次动画到来 或者 切换到下一个文本才会重新绘制视图
 * 如果 文本列表只有一个元素 那么此策略将失效
 */
const val STRATEGY_TEXT_UPDATE_LAZY: Short = 601

/**
 * ● 重新加载更新策略：当重新绘制的时候是否重新执行动画
 */
const val STRATEGY_ANIMATION_UPDATE_RESTART: Short = 602

/**
 * ● 默认更新策略：当重新绘制的时候是否继续执行已停止的动画
 */
const val STRATEGY_ANIMATION_UPDATE_DEFAULT: Short = 603

/**
 * ● PX策略 和 DP策略
 */
const val STRATEGY_DIMENSION_PX: Short = 604
const val STRATEGY_DIMENSION_DP_SP: Short = 605
```