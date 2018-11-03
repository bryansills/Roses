package ninja.bryansills.repo

import io.reactivex.Flowable
import io.reactivex.Single
import ninja.bryansills.database.DatabaseService
import ninja.bryansills.network.NetworkService
import java.util.Calendar
import java.util.Date

class RealRepository(var networkService: NetworkService, var databaseService: DatabaseService) : Repository {
    override fun categories(): Single<List<Category>> {
        return databaseService.getLastUpdated()
            .flatMap { timestamp ->
                if(isOutdated(timestamp)) {
                    fetchNetworkThenGetDatabaseCategories()
                } else {
                    getDatabaseCategories()
                }
            }
    }

    override fun getEntries(categoryId: String): Flowable<List<Entry>> {
        return databaseService.getEntries(categoryId).map {
            dbEntries -> dbEntries.map { Entry(it.id, it.title, it.url, Date(it.published), it.author, it.summary) } }
    }

    private fun isOutdated(timestamp: Long): Boolean {
        val current = Calendar.getInstance()
        current.add(Calendar.HOUR, -3)
        val currentDate = current.time

        return currentDate.after(Date(timestamp))
    }

    private fun fetchNetworkThenGetDatabaseCategories(): Single<List<Category>> {
        return networkService.streamContents()
                .map { response -> response.items.toList() }
                .flatMapCompletable { items -> databaseService.insertEntries(items) }
                .andThen(getDatabaseCategories())

    }

    private fun getDatabaseCategories(): Single<List<Category>> {
        return databaseService.getCategories()
            .map { categories -> categories.map { Category(it.id, it.title, it.count) } }
    }
}
