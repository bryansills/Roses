package ninja.bryansills.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "origins")
data class Origin(
        @PrimaryKey(autoGenerate = true) val id: Long? = null,
        @ColumnInfo(name = "network_id") val networkId: String,
        val title: String,
        @ColumnInfo(name = "html_url") val htmlUrl: String
)