package ninja.bryansills.roses.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import ninja.bryansills.roses.database.models.Origin

@Dao
interface OriginDao {
    @Query("SELECT * FROM origins WHERE network_id = :networkId LIMIT 1")
    suspend fun getOrigin(networkId: String): Origin?

    @Insert
    suspend fun insertOrigin(origin: Origin): Long

    @Update
    suspend fun updateOrigin(origin: Origin): Int

    @Transaction
    suspend fun upsertOrigin(origin: Origin): Long {
        val existingEntry = getOrigin(origin.networkId)

        return if (existingEntry != null) {
            val existingId = existingEntry.id ?: -1
            updateOrigin(origin.copy(id = existingId))

            existingId
        } else {
            insertOrigin(origin)
        }
    }
}
