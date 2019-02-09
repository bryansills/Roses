package ninja.bryansills.background

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkerFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module(includes = [WorkerModule.Bindings::class])
class WorkerModule(val context: Context, @Named("REFRESH_INTERVAL") val refreshInterval: Int) {
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
    fun context(): Context = context

    @Provides
    @Named("REFRESH_INTERVAL")
    fun refreshInterval(): Int = refreshInterval

    @Provides
    fun workerManager(
            context: Context,
            workerFactory: WorkerFactory,
            @Named("BACKGROUND_WORK_NAME") workName: String,
            workPolicy: ExistingPeriodicWorkPolicy,
            workRequest: PeriodicWorkRequest
    ): WorkerManager = RealWorkerManager(context, workerFactory, workName, workPolicy, workRequest)

    @Provides
    fun backgroundWorkerConstraints(): Constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .build()

    @Provides
    @Named("BACKGROUND_WORK_NAME")
    fun backgroundWorkName(): String = "BACKGROUND_WORKER"

    @Provides
    fun existingWorkPolicy(): ExistingPeriodicWorkPolicy = ExistingPeriodicWorkPolicy.KEEP

    @Provides
    fun backgroundWorkRequest(
            constraints: Constraints,
            @Named("REFRESH_INTERVAL") refreshInterval: Int
    ): PeriodicWorkRequest = PeriodicWorkRequest
            .Builder(BackgroundWorker::class.java, refreshInterval.toLong(), TimeUnit.HOURS)
            .setConstraints(constraints)
            .build()
}