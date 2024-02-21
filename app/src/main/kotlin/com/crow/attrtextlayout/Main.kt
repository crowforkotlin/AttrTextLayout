import android.os.Looper
import kotlinx.coroutines.*
import kotlinx.coroutines.internal.MainDispatcherFactory
import java.lang.reflect.Modifier
import java.lang.reflect.Method

fun main() {
    // 获取 AndroidDispatcherFactory 类
    val dispatcherFactoryClass = Class.forName("kotlinx.coroutines.android.AndroidDispatcherFactory")


}
