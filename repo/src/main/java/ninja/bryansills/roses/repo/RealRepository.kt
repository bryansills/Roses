package ninja.bryansills.roses.repo

import ninja.bryansills.roses.database.DatabaseService
import ninja.bryansills.roses.network.NetworkService
import ninja.bryansills.roses.network.models.streams.EntryResponse
import retrofit2.HttpException
import java.util.Calendar
import java.util.Date

class RealRepository(
    var networkService: NetworkService,
    var databaseService: DatabaseService,
    var refreshInterval: Int
) : Repository {
    override suspend fun categories(): FetchCategoryResult {
        return try {
            val lastUpdatedTimestamp = databaseService.getLastUpdated()

            val categories = if (isOutdated(lastUpdatedTimestamp)) {
                fetchNetworkThenGetDatabaseCategories()
            } else {
                getDatabaseCategories()
            }

            FetchCategoryResult.Success(categories)
        } catch (error: Exception) {
            when (error) {
                is HttpException -> extractNetworkErrorCode(error)
                else -> FetchCategoryResult.Error(FetchCategoryResult.FetchCategoryError.UNKNOWN)
            }
        }
    }

    override suspend fun getEntries(categoryId: String): FetchEntryResult {
        return try {
            val databaseEntries = databaseService.getEntries(categoryId)
            val repoEntries = databaseEntries.map { db ->
                Entry(db.id, db.title, db.url, Date(db.published), db.author, db.summary)
            }

            FetchEntryResult.Success(repoEntries)
        } catch (error: Exception) {
            FetchEntryResult.Error(FetchEntryResult.FetchEntryError.UNKNOWN)
        }
    }

    override suspend fun updateDatabase() {
        val networkResponse = networkService.streamContents()
        insertEntries(networkResponse.items.toList())
    }

    private suspend fun insertEntries(entries: List<EntryResponse>) {
        val mapping = entries.groupBy { entry -> entry.origin.streamId }
            .values
            .associateBy({ it.toOrigin() }, { it.toDbEntries() })

        databaseService.insertMappedEntries(mapping)
    }

    private fun isOutdated(timestamp: Long): Boolean {
        val current = Calendar.getInstance()
        current.add(Calendar.HOUR, -refreshInterval)
        val currentDate = current.time

        return currentDate.after(Date(timestamp))
    }

    private suspend fun fetchNetworkThenGetDatabaseCategories(): List<Category> {
        updateDatabase()
        return getDatabaseCategories()
    }

    private suspend fun getDatabaseCategories(): List<Category> {
        return databaseService.getCategories()
            .map { db -> Category(db.id, db.title, db.count) }
    }

    private fun extractNetworkErrorCode(error: HttpException): FetchCategoryResult {
        return when (error.code()) {
            401 -> FetchCategoryResult.Error(FetchCategoryResult.FetchCategoryError.API_KEY_INVALID)
            429 -> FetchCategoryResult.Error(
                FetchCategoryResult.FetchCategoryError.RATE_LIMIT_REACHED
            )
            else -> FetchCategoryResult.Error(FetchCategoryResult.FetchCategoryError.UNKNOWN)
        }
    }
}
