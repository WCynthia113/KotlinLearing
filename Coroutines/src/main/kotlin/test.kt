import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

fun main {

    run {  }
    thread {

    }
    println(
            " mainThreadId = "
                    + Thread.currentThread().id
                    + ",,,threadName = " + Thread.currentThread().name
                    + ",,,时间" + Date().toString()
    )
    val executorService = Executors.newSingleThreadScheduledExecutor()
    executorService.scheduleWithFixedDelay({
        println(
                " 开始 threadId = "
                        + Thread.currentThread().id
                        + ",,,threadName = " + Thread.currentThread().name
                        + ",,,时间" + Date().toString()
        )
    },1,5, TimeUnit.SECONDS)
}