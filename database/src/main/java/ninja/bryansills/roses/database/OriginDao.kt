package ninja.bryansills.roses.database

import androidx.room.Dao
import androidx.room.EmptyResultSetException
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import io.reactivex.Single
import ninja.bryansills.roses.database.models.Origin

@Dao
interface OriginDao {
    @Query("SELECT * FROM origins WHERE network_id = :networkId LIMIT 1")
    fun getOrigin(networkId: String): Single<Origin>

    @Insert
    fun insertOrigin(origin: Origin): Single<Long>

    @Update
    fun updateOrigin(origin: Origin): Single<Int>

    @Transaction
    fun upsertOrigin(origin: Origin): Single<Long> {
        return getOrigin(origin.networkId)
                .flatMap { databaseValue ->
                    val existingId = databaseValue.id ?: -1
                    updateOrigin(origin.copy(databaseValue.id))
                            .flatMap { Single.just(existingId) }
                }.onErrorResumeNext { throwable ->
                    if (throwable is EmptyResultSetException) {
                        insertOrigin(origin)
                    } else {
                        Single.error(throwable)
                    }
                }
    }
}