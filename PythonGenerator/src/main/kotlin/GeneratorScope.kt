interface GeneratorScope<T> {
    suspend fun yield(value: T)
}