package ninja.bryansills.roses.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import ninja.bryansills.roses.coroutine.CoroutineDispatchers

class TestCoroutineDispatchers : CoroutineDispatchers {
    override val UI: CoroutineDispatcher
        get() = TestCoroutineDispatchers().UI
    override val IO: CoroutineDispatcher
        get() = Dispatchers.Unconfined
    override val Computation: CoroutineDispatcher
        get() = Dispatchers.Unconfined
}
