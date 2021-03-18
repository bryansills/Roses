package ninja.bryansills.background

import android.content.Context
import androidx.work.WorkerParameters
import javax.inject.Inject
import ninja.bryansills.roses.repo.Repository

class BackgroundWorkerFactory @Inject constructor(val repository: Repository) : RosesWorkerFactory {
    override fun create(context: Context, workerParameters: WorkerParameters): BackgroundWorker {
        return BackgroundWorker(context, workerParameters, repository)
    }
}
