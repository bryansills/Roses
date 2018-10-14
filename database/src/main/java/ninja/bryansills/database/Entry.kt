package ninja.bryansills.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "entries",
        foreignKeys = [ForeignKey(entity = Origin::class,
                                  parentColumns = ["id"],
                                  childColumns = ["origin_id"])])
data class Entry(
        @PrimaryKey val id: String,
        val title: String?,
        val url: String?,
        val published: Long,
        val author: String?,
        @ColumnInfo(name = "origin_id") val originId: Long?
)