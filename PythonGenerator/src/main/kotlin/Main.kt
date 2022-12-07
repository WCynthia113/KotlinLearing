fun main(args: Array<String>) {
    val numbs = generator<Int> { start ->
        for (i in 0..5) {
            yield(start + i)
        }
    }
    val gen = numbs(10)
    for (j in gen) {
        println(j)
    }
}