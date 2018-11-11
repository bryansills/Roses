package ninja.bryansills.roses

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import javax.inject.Inject
import javax.inject.Provider

class RosesWorkerFactory
    @Inject constructor(val workerMap: @JvmSuppressWildcards Map<Class<*>, Provider<BackgroundWorkerFactory>>)
    : WorkerFactory() {

    override fun createWorker(appContext: Context, workerClassName: String, workerParameters: WorkerParameters): ListenableWorker {
        val factory = workerMap.filter { it.key.isAssignableFrom(Class.forName(workerClassName)) }
                .values
                .firstOrNull()?.get()
                ?: throw IllegalStateException("No Worker found")

        return factory.create(appContext, workerParameters)
    }

}