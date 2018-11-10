package ninja.bryansills.database

import android.content.Context
import androidx.room.Room
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import ninja.bryansills.network.streams.EntryResponse
import org.jsoup.Jsoup
import java.util.Date

class DatabaseService(context: Context) {
    companion object {
        const val MISSING_TIMESTAMP = -1L
    }

    private val appDatabase: AppDatabase = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "roses.db")
            .fallbackToDestructiveMigration()
            .build()


    fun getCategories(): Observable<List<Category>> {
        return appDatabase.categoryDao().getAllCategories()
    }

    fun insertEntries(entries: List<EntryResponse>): Completable {
        val grouping = entries.groupBy { entry -> entry.origin.streamId }

        return Observable.fromIterable(grouping.values).flatMapCompletable { insertGroupOfEntries(it) }
    }

    fun getEntries(categoryId: String): Flowable<List<Entry>> {
        return appDatabase.entryDao().getEntries(categoryId)
    }

    fun getLastUpdated(): Single<Long> {
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