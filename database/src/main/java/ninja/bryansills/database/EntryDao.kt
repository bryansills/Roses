package ninja.bryansills.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import ninja.bryansills.database.models.Entry

@Dao
interface EntryDao {
    @Query("SELECT * FROM entries")
    fun getAllEntries(): Flowable<List<Entry>>

    @Query("SELECT * FROM entries WHERE origin_id = :categoryId")
    fun getEntries(categoryId: String): Flowable<List<Entry>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEntries(entries: List<Entry>): Completable

    @Query("SELECT updated_at FROM entries ORDER BY updated_at DESC LIMIT 1")
    fun getLastUpdatedAt(): Single<Long>
}