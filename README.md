# AttrTextLayout
- 虽然 GIF 动画展示的效果可能看起来有些卡顿，但实际动画运行是非常平滑的。GIF 的逐帧录制方式有时可能无法完全捕捉到流畅的动态效果。
<table>
	<tr>
		<td align="center" style="padding: 10px;"><img src="docs/img/EraseXLeft.gif"></td>
		<td align="center" style="padding: 10px;"><img src="docs/img/Rhombus.gif"></td>
	</tr>
    <tr>
		<td align="center" style="padding: 10px;">Continuation-EraseX - X轴往左连续擦除</td>
		<td align="center" style="padding: 10px;">Continuation-Rhombus - 连续菱形</td>
	</tr>
</table>


---

- 这是一个属性文本布局控件，仅支持原生使用，能实现以下功能
- 支持各种文本的特效、例如四个方向移动、擦除、百叶窗、跑马灯、等特效
- 支持多行（自动、手动换行）、单行、更新文本时继续执行特效
- 支持配置多种文本策略
- 毫秒级绘制处理，最大支持200个组件异步执行特效（略有卡顿）稳定50个