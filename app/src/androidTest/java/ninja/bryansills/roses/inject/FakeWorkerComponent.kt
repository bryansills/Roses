package ninja.bryansills.roses.inject

import dagger.Component
import dagger.Module
import dagger.Provides
import ninja.bryansills.background.WorkerManager

@Component(modules = [FakeWorkerModule::class])
interface FakeWorkerComponent {
    fun fakeWorkerManager(): WorkerManager

    @Component.Builder
    interface Builder {
        fun build(): FakeWorkerComponent
        fun fakeWorkerModule(fakeWorkerModule: FakeWorkerModule): Builder
    }
}

@Module
class FakeWorkerModule {
    @Provides
    fun fakeWorkerManager(): WorkerManager = FakeWorkerManager()
}

class FakeWorkerManager : WorkerManager