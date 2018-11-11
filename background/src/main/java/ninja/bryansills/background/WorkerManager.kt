package ninja.bryansills.background

import android.content.Context
import androidx.work.Configuration
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import androidx.work.WorkerFactory

class WorkerManager(context: Context, workerFactory: WorkerFactory, workName: String,
                    workPolicy: ExistingPeriodicWorkPolicy, workRequest: PeriodicWorkRequest) {
    init {
        WorkManager.initialize(context, Configuration.Builder()
                .setWorkerFactory(workerFactory)
                .build())
        WorkManager.getInstance().enqueueUniquePeriodicWork(workName, workPolicy, workRequest)
    }
}