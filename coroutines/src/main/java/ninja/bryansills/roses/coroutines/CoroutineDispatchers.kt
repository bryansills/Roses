package ninja.bryansills.roses.coroutines

import kotlinx.coroutines.CoroutineDispatcher

interface CoroutineDispatchers {
    val UI: CoroutineDispatcher
    val IO: CoroutineDispatcher
    val Computation: CoroutineDispatcher
}
