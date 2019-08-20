package ninja.bryansills.roses.database

import ninja.bryansills.roses.database.models.Category
import ninja.bryansills.roses.database.models.Entry
import ninja.bryansills.roses.database.models.Origin

interface DatabaseService {
    suspend fun getLastUpdated(): Long

    suspend fun getEntries(categoryId: String): List<Entry>

    suspend fun insertMappedEntries(entries: Map<Origin, List<Entry>>)

    suspend fun getCategories(): List<Category>
}