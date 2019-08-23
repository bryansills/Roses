package ninja.bryansills.roses.inject

import dagger.Module
import dagger.Provides
import ninja.bryansills.roses.coroutine.CoroutineDispatchers
import ninja.bryansills.roses.coroutine.RealCoroutineDispatchers

@Module
class CoroutineModule {
    @Provides
    fun coroutineDispatchers(): CoroutineDispatchers = RealCoroutineDispatchers()
}