package ninja.bryansills.roses.database

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import ninja.bryansills.roses.database.models.Category
import ninja.bryansills.roses.database.models.Entry
import ninja.bryansills.roses.database.models.Origin
import ninja.bryansills.roses.network.models.streams.EntryResponse
import org.jsoup.Jsoup
import java.util.Date
import javax.inject.Inject

class RealDatabaseService @Inject constructor(val appDatabase: AppDatabase) : DatabaseService {
    companion object {
        const val MISSING_TIMESTAMP = -1L
    }

    override fun getCategories(): Observable<List<Category>> {
        return appDatabase.categoryDao().getAllCategories()
    }

    override fun insertEntries(entries: List<EntryResponse>): Completable {
        val grouping = entries.groupBy { entry -> entry.origin.streamId }

        return Observable.fromIterable(grouping.values).flatMapCompletable { insertGroupOfEntries(it) }
    }

    override fun getEntries(categoryId: String): Flowable<List<Entry>> {
        return appDatabase.entryDao().getEntries(categoryId)
    }

    override fun getLastUpdated(): Single<Long> {
        return appDatabase.entryDao().getLastUpdatedAt()
                .onErrorReturn { MISSING_TIMESTAMP }
    }

    private fun insertGroupOfEntries(entries: List<EntryResponse>): Completable {
        val timestamp = Date().time
        val origin = entries[0].origin
        val dbOriginId = appDatabase.originDao().upsertOrigin(Origin(null, origin.streamId, origin.title, origin.htmlUrl))
        val dbEntries = entries.map { netEntry ->
            Entry(netEntry.id,
                    netEntry.title,
                    netEntry.url,
                    netEntry.published,
                    netEntry.author,
                    netEntry.summary?.run { cleanHtml(content) },
                    timestamp,
                    dbOriginId)
        }

        return appDatabase.entryDao().insertEntries(dbEntries)
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