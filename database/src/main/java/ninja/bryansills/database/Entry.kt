package ninja.bryansills.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "entries")
data class Entry(
        @PrimaryKey @ColumnInfo(name = "id") val id: String,
        @ColumnInfo(name = "title") val title: String?,
        @ColumnInfo(name = "url") val url: String?
)