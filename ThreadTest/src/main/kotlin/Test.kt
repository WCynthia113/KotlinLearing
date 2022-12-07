import java.util.concurrent.CompletableFuture
import kotlin.concurrent.thread
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

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