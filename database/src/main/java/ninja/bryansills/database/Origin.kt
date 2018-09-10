package ninja.bryansills.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "origins")
data class Origin(
        @ColumnInfo(name = "network_id") val networkId: String,
        val title: String,
        @ColumnInfo(name = "html_url") val htmlUrl: String,
        @PrimaryKey(autoGenerate = true) val id: Long = 0
)