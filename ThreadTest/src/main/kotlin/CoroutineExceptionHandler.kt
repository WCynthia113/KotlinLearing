import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext

class CoroutineExceptionHandler(val onErrorAction: (Throwable) -> Unit): AbstractCoroutineContextElement(Key) {
    companion object Key: CoroutineContext.Key<CoroutineExceptionHandler>

    fun onError(error: Throwable) {
        error.printStackTrace()
        onErrorAction(error)
    }
}