import kotlinx.coroutines.delay

class ProducerScope<T> {
    suspend fun produce(value: T) {
        println(
            "launchCoroutineThreadId = "
                    + Thread.currentThread().id
        )
        println("produce: $value")
    }
}

fun callLaunchCoroutine() {
    launchCoroutine(ProducerScope<Int>()) {

//        println(currentCoroutineContext())
        println(
            "launchCoroutineThreadId = "
                    + Thread.currentThread().id
        )
        println("In Coroutine.")
        produce(1024)
        delay(1000)
        produce(2048)
        100
    }
}