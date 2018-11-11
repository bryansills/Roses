package ninja.bryansills.roses.inject

import androidx.work.WorkerFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import ninja.bryansills.background.BackgroundWorker
import ninja.bryansills.roses.BackgroundWorkerFactory
import ninja.bryansills.roses.RosesWorkerFactory

@Module
interface WorkerModule {
    @Binds
    fun bindWorkerFactory(rosesWorkerFactory: RosesWorkerFactory): WorkerFactory

    @Binds
    @IntoMap
    @ClassKey(BackgroundWorker::class)
    fun bindBackgroundWorkerFactory(backgroundWorkerFactory: BackgroundWorkerFactory): BackgroundWorkerFactory
}