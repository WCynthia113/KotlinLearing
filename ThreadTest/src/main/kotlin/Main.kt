import kotlinx.coroutines.*
import kotlin.concurrent.thread
import kotlin.coroutines.*

//suspend fun main() {
//    var coroutineContext: CoroutineContext = EmptyCoroutineContext
//    coroutineContext += CoroutineName("co-01")
//    coroutineContext += LogInterceptor()
//    coroutineContext += CoroutineExceptionHandler {
//
//    }
//    val continuation = suspend {
//        println(
//            "suspendThreadId = "
//                    + Thread.currentThread().id
//        )
//        println("In Coroutine.")
//        println((coroutineContext[CoroutineName] as CoroutineName).name)
//        5
//    }.createCoroutine(object : Continuation<Int> {
//        override val context: CoroutineContext
//            get() = coroutineContext
//
//        override fun resumeWith(result: Result<Int>) {
//            println(
//                "resumeWithThreadId = "
//                        + Thread.currentThread().id
//            )
//            println("Coroutine End: $result")
//        }
//    })
//
//
//    continuation.resume(Unit)
//    println("main end")
//    delay(1000)
//}

fun main() {
    suspend {
        suspendFunc01(1)
        suspendFunc02("Hello", "World")
        suspendFunc02("Hello", "Kotlin")
    }.startCoroutine(object : Continuation<Int> {
        override val context: CoroutineContext
            get() = LogInterceptor()

        override fun resumeWith(result: Result<Int>) {
            println("Coroutine End: $result")
        }

    })
}

suspend fun suspendFunc01(a: Int) {}
suspend fun suspendFunc02(a: String, b: String) = suspendCoroutine<Int> {
    thread {
        println("suspendFunc02")
        it.resumeWith(Result.success(5))
    }
}



