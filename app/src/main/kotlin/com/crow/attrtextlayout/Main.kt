package com.crow.attrtextlayout

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors

class Main {
    var job : Job? = null
    var value = 0
}
val main1 = Main()
val main2 = Main()
val ui = CoroutineScope(Executors.newSingleThreadExecutor().asCoroutineDispatcher())
fun main() = runBlocking {
    val scope = CoroutineScope(Executors.newSingleThreadExecutor().asCoroutineDispatcher())
    val job = scope.launch {
        running(scope, main1)
        delay(Long.MAX_VALUE)
    }
    scope.async {delay(1000)
        running(scope, main2) }
    job.join()
    println("----------------------------------------")
    println(main1.value)
    println(main2.value)
    this.coroutineContext.job.join()
}


suspend fun running(scope: CoroutineScope, main: Main) {
    println("!23")
    return
    repeat(127) {
        main.job = scope.launch {
            main.value ++
            println("${main.value} \t $it")
            cancelJob(main)
            delay(10)
        }
        main.job?.join()
    }
}

fun cancelJob(main: Main) {
    ui.launch {
        delay(20)
        main1.job?.cancel()
    }
}
