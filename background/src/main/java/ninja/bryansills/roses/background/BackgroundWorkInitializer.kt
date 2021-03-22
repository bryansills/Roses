package ninja.bryansills.roses.background

import android.content.Context
import androidx.startup.Initializer
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import javax.inject.Inject
import javax.inject.Named

class BackgroundWorkInitializer : Initializer<Unit> {
    @Inject
    @Named("BACKGROUND_WORK_NAME")
    lateinit var backgroundWorkName: String

    @Inject
    lateinit var existingPeriodicWorkPolicy: ExistingPeriodicWorkPolicy

    @Inject
    lateinit var backgroundWorkRequest: PeriodicWorkRequest

    override fun create(context: Context) {
        InitializerEntryPoint.resolve(context).inject(this)
        val workManager = WorkManager.getInstance(context)
        workManager.enqueueUniquePeriodicWork(
            backgroundWorkName,
            existingPeriodicWorkPolicy,
            backgroundWorkRequest
        )
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return listOf(WorkManagerInitializer::class.java)
    }
}
