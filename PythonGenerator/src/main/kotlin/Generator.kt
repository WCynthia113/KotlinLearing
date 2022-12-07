import kotlin.coroutines.Continuation

interface Generator<T> {
    operator fun iterator(): Iterator<T>
    sealed class State {
        class NotReady(val continuation: Continuation<Unit>): State()
        class Ready<T>(val continuation: Continuation<Unit>, val nextValue: T): State()
        object Done: State()
    }
}

fun <T> generator(block: suspend GeneratorScope<T>.(T) -> Unit):(T) -> Generator<T> {
    return {parameter:T ->
        GeneratorImpl(block, parameter)
    }
}

class GeneratorImpl<T>(
    private val block: suspend  GeneratorScope<T>.(T) -> Unit,
    private val parameter: T
): Generator<T> {
    override fun iterator(): Iterator<T> {
        return GeneratorIterator(block, parameter)
    }
}
