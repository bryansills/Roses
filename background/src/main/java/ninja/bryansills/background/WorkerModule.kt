package ninja.bryansills.background

import android.content.Context
import androidx.work.WorkManager
import androidx.work.WorkerFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(includes = [WorkerModule.Bindings::class])
class WorkerModule {
    @Module
    interface Bindings {
        @Binds
        fun bindWorkerFactory(workerFactoryProvider: WorkerFactoryProvider): WorkerFactory

        @Binds
        @IntoMap
        @ClassKey(BackgroundWorker::class)
        fun bindBackgroundWorkerFactory(workerFactory: BackgroundWorkerFactory): RosesWorkerFactory
    }

        @Provides
        fun workerManager(context: Context, workerFactory: WorkerFactory)
                = WorkerManager(context, workerFactory)

//    @Provides
//    fun backgroundWorkerConstraints(): Constraints = Constraints.Builder()
//            .setRequiredNetworkType(NetworkType.CONNECTED)
//            .setRequiresBatteryNotLow(true)
//            .build()
}