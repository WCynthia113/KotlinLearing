import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext

class CoroutineName(val name: String): AbstractCoroutineContextElement(Key){
    companion object Key: CoroutineContext.Key<CoroutineName>
}