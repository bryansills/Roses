package ninja.bryansills.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ninja.bryansills.database.models.Entry
import ninja.bryansills.database.models.Origin

@Database(entities = [Entry::class, Origin::class],
          version = 6)
abstract class AppDatabase : RoomDatabase() {
    abstract fun entryDao(): EntryDao
    abstract fun originDao(): OriginDao
    abstract fun categoryDao(): CategoryDao
}