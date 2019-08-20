package ninja.bryansills.roses.database

import ninja.bryansills.roses.database.models.Category
import ninja.bryansills.roses.database.models.Entry
import ninja.bryansills.roses.database.models.Origin
import org.jsoup.Jsoup
import javax.inject.Inject

class RealDatabaseService @Inject constructor(val appDatabase: AppDatabase) : DatabaseService {
    companion object {
        const val MISSING_TIMESTAMP = -1L
    }

    override suspend fun getCategories(): List<Category> {
        return appDatabase.categoryDao().getAllCategories()
    }

    override suspend fun insertMappedEntries(entries: Map<Origin, List<Entry>>) {
        entries.entries.forEach { insertGroupOfEntries(it) }
    }

    override suspend fun getEntries(categoryId: String): List<Entry> {
        return appDatabase.entryDao().getEntries(categoryId)
    }

    override suspend fun getLastUpdated(): Long {
        return appDatabase.entryDao().getLastUpdatedAt()
    }

    private suspend fun insertGroupOfEntries(entryGroup: Map.Entry<Origin, List<Entry>>) {
        val originId = appDatabase.originDao().upsertOrigin(entryGroup.key)

        val dbReady = entryGroup.value.map { singleEntry ->
            val cleanedSummary = cleanHtml(singleEntry.summary ?: "")
            singleEntry.copy(originId = originId, summary = cleanedSummary)
        }
        appDatabase.entryDao().insertEntries(dbReady)
    }

    private fun cleanHtml(html: String): String {
        val document = Jsoup.parse(html)

        val media = document.select("[src]")
        media.forEach {
            it.remove()
        }

        return document.html()
    }
}