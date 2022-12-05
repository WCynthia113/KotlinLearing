import kotlinx.coroutines.*
import java.net.URL
import java.util.*
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.Callable
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread
import kotlin.coroutines.*

suspend fun main() {
    println(
        " suspendThreadId = "
                + Thread.currentThread().id
    )
//    val continuation = suspend {
//        println(
//            " suspendThreadId = "
//                    + Thread.currentThread().id
//        )
//        print("In Coroutine.")
//        5
//    }.startCoroutine(object: Continuation<Int> {
//        override val context: CoroutineContext
//            get() = EmptyCoroutineContext
//
//        override fun resumeWith(result: Result<Int>) {println(
//            " suspendThreadId = "
//                    + Thread.currentThread().id
//        )
//            println("Coroutine End: $result")
//        }
//
//    })
//    continuation.resume(Unit)
    callLaunchCoroutine()
    delay(100)
}

fun <R, T> launchCoroutine(receiver: R, block: suspend R.() -> T) {
    block.startCoroutine(receiver, object : Continuation<T> {
        override val context = EmptyCoroutineContext

        override fun resumeWith(result: Result<T>) {
            println("Coroutine End: $result")
        }

    })
}

class ProducerScope<T> {
    suspend fun produce(value: T) {
        println("$value")
    }
}

@RestrictsSuspension
class RestrictProducerScope<T> {
    suspend fun produce(value: T) {
        println("$value")
    }
}

fun callLaunchCoroutine() {
    launchCoroutine(ProducerScope<Int>()) {
        println(
            " suspendThreadId = "
                    + Thread.currentThread().id
        )
        println("In Coroutine.")
        produce(1024)
        delay(1000)
        produce(2048)
    }
}
//fun main(args: Array<String>) {
//    Callable {
//
//    }
//    val ioExecutor = Executors.newSingleThreadExecutor()
//    val future = ioExecutor.submit {
//
//    }
//    future.cancel(true)
//
//    val thread = thread {  }
//    val url:String = "sdf"
//    url.transform {
//        bitmapCompletableFuture(it)
//    }.let {
//        CompletableFuture.allOf().thenApply {
//        }
//    }
//    thread.interrupt()
//    println(
//        " mainThreadId = "
//                + Thread.currentThread().id
//                + ",,,threadName = " + Thread.currentThread().name
//                + ",,,时间" + Date().toString()
//    )
//    val executorService = Executors.newSingleThreadScheduledExecutor()
//    executorService.scheduleWithFixedDelay({        println(
//            " 开始 threadId = "
//                    + Thread.currentThread().id
//                    + ",,,threadName = " + Thread.currentThread().name
//                    + ",,,时间" + Date().toString()
//        )
//        GlobalScope.launch {
//            println(
//            " GlobalScope threadId = "
//                    + Thread.currentThread().id
//                    + ",,,threadName = " + Thread.currentThread().name
//                    + ",,,时间" + Date().toString()
//            )
//            withContext(Dispatchers.Default ) {
//                println(
//                    " Main threadId = "
//                            + Thread.currentThread().id
//                            + ",,,threadName = " + Thread.currentThread().name
//                            + ",,,时间" + Date().toString()
//                )
//            }
//        }
//    },1,5, TimeUnit.SECONDS)
//}

fun bitmapCompletableFuture(url: String): CompletableFuture<Boolean> = CompletableFuture.supplyAsync {
    return@supplyAsync false
}

suspend fun bitmapSuspendable(url: String): Boolean =
    suspendCoroutine {
        thread {
            try {
                it.resume(true)
            } catch (e: Exception) {
                it.resumeWithException(e)
            }
        }
    }