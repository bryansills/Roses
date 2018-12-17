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
    fun getOrigin(networkId: String): Origin?

    @Insert
    fun insertOrigin(origin: Origin): Long

    @Update
    fun updateOrigin(origin: Origin): Int

    @Transaction
    fun upsertOrigin(origin: Origin): Long {
        val existingOrigin = getOrigin(origin.networkId)

        return if (existingOrigin != null) {
            updateOrigin(origin.copy(id = existingOrigin.id))
            existingOrigin.id ?: -1
        } else {
            insertOrigin(origin)
        }
    }
}