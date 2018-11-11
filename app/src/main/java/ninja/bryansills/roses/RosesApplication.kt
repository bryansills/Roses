package ninja.bryansills.roses

import androidx.work.Configuration
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import androidx.work.WorkerFactory
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import ninja.bryansills.background.BackgroundWorker
import ninja.bryansills.roses.inject.DaggerApplicationComponent
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class RosesApplication : DaggerApplication() {

    @Inject lateinit var workerFactory: WorkerFactory

    override fun onCreate() {
        super.onCreate()

        WorkManager.initialize(this, Configuration.Builder()
                .setWorkerFactory(workerFactory)
                .build())

        val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresBatteryNotLow(true)
                .build()
        val request = PeriodicWorkRequest
                .Builder(BackgroundWorker::class.java, 15, TimeUnit.MINUTES)
                .addTag("BLARG")
                .setConstraints(constraints)
                .build()
        WorkManager.getInstance().enqueue(request)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
            DaggerApplicationComponent.builder().create(this)
}
