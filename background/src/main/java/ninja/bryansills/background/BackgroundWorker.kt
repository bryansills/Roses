package ninja.bryansills.background

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import ninja.bryansills.repo.Repository

class BackgroundWorker(
    context: Context,
    workerParams: WorkerParameters,
    val repository: Repository
) : CoroutineWorker(context, workerParams) {

    // this does something
    override suspend fun doWork(): Result = coroutineScope {
        try {
            withContext(Dispatchers.IO) {
                repository.updateDatabase()
            }

            Result.success()
        } catch (throwable: Throwable) {
            Log.d("BLARG", throwable.message ?: "")
            Result.failure()
        }
    }

    // here is some dumb code
}
