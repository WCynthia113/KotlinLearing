import kotlinx.coroutines.newSingleThreadContext
import kotlin.coroutines.Continuation
import kotlin.coroutines.startCoroutine

fun <R, T> launchCoroutine(receiver: R, block: suspend R.() -> T) {
    block.startCoroutine(receiver, object : Continuation<T> {
        override val context = newSingleThreadContext("1")

        override fun resumeWith(result: Result<T>) {
//            println(context)
            println(
                "resumeWithThreadId = "
                        + Thread.currentThread().id
            )
            println("Coroutine End: $result")
        }
    })
}
