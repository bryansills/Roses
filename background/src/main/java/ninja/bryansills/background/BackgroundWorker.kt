package ninja.bryansills.background

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class BackgroundWorker(context: Context,
                       workerParams: WorkerParameters)
    : Worker(context, workerParams) {

    override fun doWork(): Result {
        return try {
//            repository.updateDatabase()
//                    .subscribeOn(Schedulers.io())
//                    .blockingAwait()
            Log.d("BLARG", "we here!")
            Result.SUCCESS
        } catch (throwable: Throwable) {
            Log.d("BLARG", throwable.message)
            Result.FAILURE
        }
    }
}