package ninja.bryansills.background

import android.content.Context
import androidx.work.WorkerParameters
import ninja.bryansills.repo.Repository
import javax.inject.Inject

class BackgroundWorkerFactory @Inject constructor(val repository: Repository) : RosesWorkerFactory {
    override fun create(context: Context, workerParameters: WorkerParameters): BackgroundWorker {
        return BackgroundWorker(context, workerParameters, repository)
    }
}