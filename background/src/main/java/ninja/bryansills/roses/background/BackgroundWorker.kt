package ninja.bryansills.roses.background

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import ninja.bryansills.roses.coroutines.CoroutineDispatchers
import ninja.bryansills.roses.repo.Repository

@HiltWorker
class BackgroundWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val repository: Repository,
    private val coroutineDispatchers: CoroutineDispatchers
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result = coroutineScope {
        try {
            withContext(coroutineDispatchers.IO) {
                repository.updateDatabase()
            }

            Result.success()
        } catch (throwable: Throwable) {
            Result.failure()
        }
    }
}
