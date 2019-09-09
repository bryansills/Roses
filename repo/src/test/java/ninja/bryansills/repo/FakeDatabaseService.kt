package ninja.bryansills.repo

import java.util.*
import ninja.bryansills.database.test.DatabaseTestUtils
import ninja.bryansills.roses.database.DatabaseService
import ninja.bryansills.roses.database.models.Category
import ninja.bryansills.roses.database.models.Entry
import ninja.bryansills.roses.database.models.Origin

class FakeDatabaseService : DatabaseService {

    var hasCategoriesBeenCalled = false

    var lastUpdated = Date().time
    var categories = listOf<Category>()

    override suspend fun getLastUpdated() = lastUpdated

    override suspend fun getEntries(categoryId: String) =
        listOf(DatabaseTestUtils.createEntry(1, 1L, 1L))

    override suspend fun insertMappedEntries(entries: Map<Origin, List<Entry>>) {}

    override suspend fun getCategories(): List<Category> {
        hasCategoriesBeenCalled = true
        return categories
    }
}