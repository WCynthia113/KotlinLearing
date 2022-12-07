import kotlinx.coroutines.delay
import kotlin.coroutines.RestrictsSuspension

@RestrictsSuspension
class RestrictProducerScope<T> {
    suspend fun produce(value: T) {
//        println(currentCoroutineContext())
        println(
            "launchCoroutineThreadId = "
                    + Thread.currentThread().id
        )
        println("$value")
    }
}

fun callLaunchCoroutineRestricted() {
    launchCoroutine(RestrictProducerScope<Int>()) {
        println(
            " suspendThreadId = "
                    + Thread.currentThread().id
        )
        println("In Coroutine.")
        produce(1024)
        produce(2048)
    }
}

