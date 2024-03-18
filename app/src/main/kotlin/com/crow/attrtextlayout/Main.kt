import kotlin.experimental.or

fun main() {
    println(123.456f.toIntArray().map { it })
}
/**
 * ⦁ 浮点数转Int字节数组，一般不会超过两个元素
 *
 * ⦁ 2024-01-16 17:26:17 周二 下午
 * @author crowforkotlin
 */
fun Float.toIntArray() = with(fromFloat32(this)) { intArrayOf(toInt16(this, 0), toInt16(this, 2)) }

fun fromFloat32(value: Float): ByteArray {
    val intBits = java.lang.Float.floatToIntBits(value)  // 将float转换为Int位表示
    return byteArrayOf(
        (intBits shr 24).toByte(),  // 第一个字节（最高有效字节）
        (intBits shr 16).toByte(),  // 第二个字节
        (intBits shr 8).toByte(),   // 第三个字节
        (intBits).toByte()          // 第四个字节（最低有效字节）
    )
}
fun toInt16(array: ByteArray, index: Int = 0): Int {
    return (((array[index].toInt() and 0xFF) shl 8).toShort() or (array[index + 1].toInt() and 0xFF).toShort()).toInt()
}