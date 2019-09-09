package ninja.bryansills.background

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import javax.inject.Inject
import javax.inject.Provider

class WorkerFactoryProvider
@Inject constructor(val rosesWorkerMap: @JvmSuppressWildcards Map<Class<*>, Provider<RosesWorkerFactory>>) :
    WorkerFactory() {

    override fun createWorker(appContext: Context, workerClassName: String, workerParameters: WorkerParameters): ListenableWorker {
        return getFactory(workerClassName).create(appContext, workerParameters)
    }

    private fun getFactory(workerClassName: String): RosesWorkerFactory {
        return rosesWorkerMap.filter { it.key.isAssignableFrom(Class.forName(workerClassName)) }
            .values
            .firstOrNull()?.get()
            ?: throw IllegalStateException("No RosesWorkerFactory found")
    }
}