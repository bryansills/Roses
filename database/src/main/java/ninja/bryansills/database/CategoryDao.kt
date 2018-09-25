package ninja.bryansills.database

import androidx.room.Dao
import androidx.room.Query
import io.reactivex.Flowable

@Dao
interface CategoryDao {
    @Query("""
        SELECT origins.id, origins.title, COUNT(entries.origin_id) as count
        FROM entries, origins
        WHERE entries.origin_id = origins.id
        GROUP BY origin_id
    """)
    fun getAllCategories(): Flowable<List<Category>>
}