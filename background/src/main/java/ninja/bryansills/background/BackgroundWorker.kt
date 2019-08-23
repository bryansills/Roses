package ninja.bryansills.background

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import ninja.bryansills.repo.Repository

class BackgroundWorker(context: Context,
                       workerParams: WorkerParameters,
                       val repository: Repository)
    : Worker(context, workerParams) {

    override fun doWork(): Result {
        return try {
//            repository.updateDatabase().blockingAwait()
            Result.success()
        } catch (throwable: Throwable) {
            Log.d("BLARG", throwable.message ?: "")
            Result.failure()
        }
    }
}
