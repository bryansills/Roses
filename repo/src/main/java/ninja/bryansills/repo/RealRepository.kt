package ninja.bryansills.repo

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import ninja.bryansills.roses.database.DatabaseService
import ninja.bryansills.network.NetworkService
import retrofit2.HttpException
import java.util.Calendar
import java.util.Date

class RealRepository(var networkService: NetworkService, var databaseService: DatabaseService, var refreshInterval: Int) : Repository {
    override fun categories(): Observable<FetchCategoryResult> {
        return databaseService.getLastUpdated()
                .flatMapObservable { timestamp ->
                    if(isOutdated(timestamp)) {
                        fetchNetworkThenGetDatabaseCategories()
                    } else {
                        getDatabaseCategories()
                    }
                }
                .map { FetchCategoryResult.Success(it) as FetchCategoryResult }
                .onErrorReturn {
                    when (it) {
                        is HttpException -> extractNetworkErrorCode(it)
                        else -> FetchCategoryResult.Error(FetchCategoryResult.FetchCategoryError.UNKNOWN)
                    }
                }
    }

    override fun getEntries(categoryId: String): Flowable<List<Entry>> {
        return databaseService.getEntries(categoryId).map {
            dbEntries -> dbEntries.map { Entry(it.id, it.title, it.url, Date(it.published), it.author, it.summary) } }
    }

    override fun updateDatabase(): Completable {
        return networkService.streamContents()
                .map { response -> response.items.toList() }
                .flatMapCompletable { items -> databaseService.insertEntries(items) }
    }

    private fun isOutdated(timestamp: Long): Boolean {
        val current = Calendar.getInstance()
        current.add(Calendar.HOUR, -refreshInterval)
        val currentDate = current.time

        return currentDate.after(Date(timestamp))
    }

    private fun fetchNetworkThenGetDatabaseCategories(): Observable<List<Category>> {
                return updateDatabase().andThen(getDatabaseCategories())

    }

    private fun getDatabaseCategories(): Observable<List<Category>> {
        return databaseService.getCategories()
            .map { categories -> categories.map { Category(it.id, it.title, it.count) } }
    }

    private fun extractNetworkErrorCode(error: HttpException): FetchCategoryResult {
        return when (error.code()) {
            401 -> FetchCategoryResult.Error(FetchCategoryResult.FetchCategoryError.API_KEY_INVALID)
            429 -> FetchCategoryResult.Error(FetchCategoryResult.FetchCategoryError.RATE_LIMIT_REACHED)
            else -> FetchCategoryResult.Error(FetchCategoryResult.FetchCategoryError.UNKNOWN)
        }
    }
}
