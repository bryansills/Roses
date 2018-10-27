package ninja.bryansills.database

import android.content.Context
import androidx.room.Room
import io.reactivex.Flowable
import ninja.bryansills.network.streams.EntryResponse
import org.jsoup.Jsoup

class DatabaseService(context: Context) {
    private val appDatabase: AppDatabase = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "roses.db")
            .fallbackToDestructiveMigration()
            .build()

    fun entries(): Flowable<List<Entry>> {
        return appDatabase.entryDao().getAllEntries()
    }

    fun categories(): Flowable<List<Category>> {
        return appDatabase.categoryDao().getAllCategories()
    }

    fun insertEntries(entries: List<EntryResponse>) {
        val grouping = entries.groupBy { entry -> entry.origin.streamId }
        grouping.values.map { insertGroupOfEntries(it) }
    }

    fun getEntries(categoryId: String): Flowable<List<Entry>> {
        return appDatabase.entryDao().getEntries(categoryId)
    }

    private fun insertGroupOfEntries(entries: List<EntryResponse>) {
        val origin = entries[0].origin
        val dbOriginId = appDatabase.originDao().upsertOrigin(Origin(null, origin.streamId, origin.title, origin.htmlUrl))
        val dbEntries = entries.map { netEntry ->
            Entry(netEntry.id,
                  netEntry.title,
                  netEntry.url,
                  netEntry.published,
                  netEntry.author,
                  netEntry.summary?.run { cleanHtml(content) },
                  dbOriginId)
        }

        appDatabase.entryDao().insertEntries(dbEntries)
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