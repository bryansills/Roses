package ninja.bryansills.background

import android.content.Context
import androidx.work.Configuration
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import androidx.work.WorkerFactory
import java.util.concurrent.TimeUnit

class WorkerManager(context: Context, workerFactory: WorkerFactory) {
    init {
        WorkManager.initialize(context, Configuration.Builder()
                .setWorkerFactory(workerFactory)
                .build())
        val backgroundWorkerConstraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresBatteryNotLow(true)
                .build()
        val request = PeriodicWorkRequest
                .Builder(BackgroundWorker::class.java, 15, TimeUnit.MINUTES)
                .setConstraints(backgroundWorkerConstraints)
                .build()
        WorkManager.getInstance().enqueue(request)
    }
}