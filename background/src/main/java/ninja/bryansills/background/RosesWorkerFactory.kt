package ninja.bryansills.background

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

interface RosesWorkerFactory {
    fun create(context: Context, workerParameters: WorkerParameters): CoroutineWorker
}