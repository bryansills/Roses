package ninja.bryansills.roses.database

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import ninja.bryansills.roses.database.models.Category
import ninja.bryansills.roses.database.models.Entry
import ninja.bryansills.roses.database.models.Origin
import org.jsoup.Jsoup
import javax.inject.Inject

class RealDatabaseService @Inject constructor(val appDatabase: AppDatabase) : DatabaseService {
    companion object {
        const val MISSING_TIMESTAMP = -1L
    }

    override fun getCategories(): Observable<List<Category>> {
        return appDatabase.categoryDao().getAllCategories()
    }

    override fun insertMappedEntries(entries: Map<Origin, List<Entry>>): Completable {
        return Observable.fromIterable(entries.entries).flatMapCompletable { insertGroupOfEntries(it)}
    }

    override fun getEntries(categoryId: String): Flowable<List<Entry>> {
        return appDatabase.entryDao().getEntries(categoryId)
    }

    override fun getLastUpdated(): Single<Long> {
        return appDatabase.entryDao().getLastUpdatedAt()
                .onErrorReturn { MISSING_TIMESTAMP }
    }

    private fun insertGroupOfEntries(entryGroup: Map.Entry<Origin, List<Entry>>): Completable {
        return appDatabase.originDao().upsertOrigin(entryGroup.key)
                .flatMapCompletable { originId ->
                    val dbReady = entryGroup.value.map { singleEntry ->
                        val cleanedSummary = cleanHtml(singleEntry.summary ?: "")
                        singleEntry.copy(originId = originId, summary = cleanedSummary)
                    }

                    appDatabase.entryDao().insertEntries(dbReady)
                }
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