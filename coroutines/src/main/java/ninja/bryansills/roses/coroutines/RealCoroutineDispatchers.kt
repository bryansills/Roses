package ninja.bryansills.roses.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class RealCoroutineDispatchers : CoroutineDispatchers {
    override val UI: CoroutineDispatcher
        get() = Dispatchers.Main

    override val IO: CoroutineDispatcher
        get() = Dispatchers.IO

    override val Computation: CoroutineDispatcher
        get() = Dispatchers.Default
}
