package ninja.bryansills.background

import androidx.work.WorkerFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
interface WorkerModule {
    @Binds
    fun bindWorkerFactory(workerFactoryProvider: WorkerFactoryProvider): WorkerFactory

    @Binds
    @IntoMap
    @ClassKey(BackgroundWorker::class)
    fun bindBackgroundWorkerFactory(workerFactory: BackgroundWorkerFactory): RosesWorkerFactory
}