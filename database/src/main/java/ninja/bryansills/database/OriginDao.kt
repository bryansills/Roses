package ninja.bryansills.database

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface OriginDao {
    @Insert()
    fun insertOrigin(origin: Origin): Long
}