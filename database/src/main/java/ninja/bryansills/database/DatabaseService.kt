package ninja.bryansills.database

import android.content.Context
import androidx.room.Room
import io.reactivex.Flowable
import ninja.bryansills.network.streams.EntryResponse

class DatabaseService(context: Context) {
    private val appDatabase: AppDatabase = Room.inMemoryDatabaseBuilder(
                context.applicationContext,
                AppDatabase::class.java)
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

    private fun insertGroupOfEntries(entries: List<EntryResponse>) {
        val origin = entries[0].origin
        val dbOriginId = appDatabase.originDao().insertOrigin(Origin(origin.streamId, origin.title, origin.htmlUrl))
        val dbEntries = entries.map { netEntry -> Entry(netEntry.id, netEntry.title, netEntry.url, dbOriginId) }

        appDatabase.entryDao().insertEntries(dbEntries)
    }
}