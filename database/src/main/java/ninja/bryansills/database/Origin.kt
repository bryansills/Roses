package ninja.bryansills.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "origins")
data class Origin(
        val networkId: String,
        val title: String,
        val htmlUrl: String,
        @PrimaryKey(autoGenerate = true) val id: Long = 0
)