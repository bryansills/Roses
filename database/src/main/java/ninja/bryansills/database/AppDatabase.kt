package ninja.bryansills.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Entry::class, Origin::class],
          version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun entryDao(): EntryDao
    abstract fun originDao(): OriginDao
    abstract fun categoryDao(): CategoryDao
}