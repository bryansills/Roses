package ninja.bryansills.roses.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ninja.bryansills.roses.database.models.Entry

@Dao
interface EntryDao {
    @Query("SELECT * FROM entries WHERE origin_id = :categoryId")
    suspend fun getEntries(categoryId: String): List<Entry>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEntries(entries: List<Entry>)

    @Query("SELECT updated_at FROM entries ORDER BY updated_at DESC LIMIT 1")
    suspend fun getLastUpdatedAt(): Long?
}