package ninja.bryansills.background

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import io.reactivex.schedulers.Schedulers
import ninja.bryansills.repo.Repository

class BackgroundWorker(context: Context,
                       workerParams: WorkerParameters,
                       private val repository: Repository)
    : Worker(context, workerParams) {

    override fun doWork(): Result {
        return try {
            repository.updateDatabase()
                    .subscribeOn(Schedulers.io())
                    .blockingAwait()
            Result.SUCCESS
        } catch (throwable: Throwable) {
            Log.d("BLARG", throwable.message)
            Result.FAILURE
        }
    }
}