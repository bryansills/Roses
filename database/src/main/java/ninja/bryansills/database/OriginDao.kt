package ninja.bryansills.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface OriginDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrigin(origin: Origin): Long
}