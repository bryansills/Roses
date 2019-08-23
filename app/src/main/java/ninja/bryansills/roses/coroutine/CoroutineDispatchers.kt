package ninja.bryansills.roses.coroutine

import kotlinx.coroutines.CoroutineDispatcher

interface CoroutineDispatchers {
    val UI: CoroutineDispatcher
    val IO: CoroutineDispatcher
    val Computation: CoroutineDispatcher
}