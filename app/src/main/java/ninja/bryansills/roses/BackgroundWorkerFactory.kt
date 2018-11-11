package ninja.bryansills.roses

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import ninja.bryansills.background.BackgroundWorker
import ninja.bryansills.repo.Repository
import javax.inject.Inject

class BackgroundWorkerFactory @Inject constructor(val repository: Repository) {
    fun create(context: Context, workerParams: WorkerParameters): BackgroundWorker {
        return BackgroundWorker(context, workerParams, repository)
    }
}