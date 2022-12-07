import java.lang.IndexOutOfBoundsException
import kotlin.coroutines.*

class GeneratorIterator<T>(
    private val block: suspend  GeneratorScope<T>.(T) -> Unit,
    private val parameter: T
): GeneratorScope<T>, Iterator<T>, Continuation<Any?> {
    override val context: CoroutineContext
        get() = EmptyCoroutineContext

    private var state: Generator.State

    init {
        val coroutineBlock: suspend GeneratorScope<T>.() -> Unit = {block(parameter)}
        val start = coroutineBlock.createCoroutine(this,this)
        state = Generator.State.NotReady(start)
    }

    override suspend fun yield(value: T) = suspendCoroutine { continuation ->
        println("yield")
        state = when(state) {
            is Generator.State.NotReady -> Generator.State.Ready(continuation, value)
            is Generator.State.Ready<*> -> throw IllegalStateException("Cannot yield while ready.")
            Generator.State.Done -> throw IllegalStateException("Cannot yield while done.")
        }
    }

    private fun resume() {
        println("resume")
        when (val currentState = state) {
            is Generator.State.NotReady -> currentState.continuation.resume(Unit)
            else -> {}
        }
    }

    override fun hasNext(): Boolean {
        println("hasNext")
        resume()
        return state != Generator.State.Done
    }

    override fun next(): T {
        println("next")
        return when(val currentState = state) {
            is Generator.State.NotReady -> {
                resume()
                next()
            }
            is Generator.State.Ready<*> -> {
                state = Generator.State.NotReady(currentState.continuation)
                (currentState as Generator.State.Ready<T>).nextValue
            }
            Generator.State.Done -> throw IndexOutOfBoundsException("No value left.")
        }
    }

    override fun resumeWith(result: Result<Any?>) {
        state = Generator.State.Done
        result.getOrThrow()
    }
}