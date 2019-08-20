package ninja.bryansills.roses.database

import androidx.room.Dao
import androidx.room.Query
import ninja.bryansills.roses.database.models.Category

@Dao
interface CategoryDao {
    @Query("""
        SELECT origins.id, origins.title, COUNT(entries.origin_id) as count
        FROM entries, origins
        WHERE entries.origin_id = origins.id
        GROUP BY origin_id
    """)
    suspend fun getAllCategories(): List<Category>
}