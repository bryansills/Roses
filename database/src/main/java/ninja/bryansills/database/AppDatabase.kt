package ninja.bryansills.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Entry::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun entryDao(): EntryDao
}