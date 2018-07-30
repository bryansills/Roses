package ninja.bryansills.database

import android.content.Context
import androidx.room.Room
import io.reactivex.Flowable

class DatabaseService(context: Context) {
    private val appDatabase: AppDatabase
    init {
        appDatabase = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "test.db")
                .build()
    }

    fun entries(): Flowable<List<Entry>> {
        return appDatabase.entryDao().getAllEntries()
    }

    fun insertEntries(entries: List<Entry>) {
        appDatabase.entryDao().insertEntries(entries)
    }
}