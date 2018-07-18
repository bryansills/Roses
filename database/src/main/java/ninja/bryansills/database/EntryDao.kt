package ninja.bryansills.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable

@Dao
interface EntryDao {
    @Query("SELECT * FROM entries")
    fun getAllEntries(): Flowable<Entry>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEntries(entries: List<Entry>)
}