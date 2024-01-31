package com.crow.attrtextlayout

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
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
    var a: Deferred<Unit>? = null
    scope.async(CoroutineExceptionHandler { coroutineContext, throwable -> println("2")}) {
        scope.launch(CoroutineExceptionHandler { coroutineContext, throwable -> println("1")}) {
            delay(2000)
            a?.cancel()
        }
        a = scope.async(CoroutineExceptionHandler { coroutineContext, throwable -> println("3")}) { delay(5000) }
        a?.await()
    }.await()
    println("end")
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
