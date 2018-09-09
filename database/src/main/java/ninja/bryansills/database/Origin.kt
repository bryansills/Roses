package ninja.bryansills.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "origins")
data class Origin(
        @PrimaryKey val id: String,
        val title: String,
        val htmlUrl: String)