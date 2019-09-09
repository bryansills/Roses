package ninja.bryansills.repo

import java.util.Date

data class Entry(
    val id: String,
    val title: String?,
    val url: String?,
    val published: Date,
    val author: String?,
    val summary: String?
)