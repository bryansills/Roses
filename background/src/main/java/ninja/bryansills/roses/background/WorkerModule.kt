package ninja.bryansills.roses.background

import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import dagger.Module
import dagger.Provides
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module
object WorkerModule {
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
