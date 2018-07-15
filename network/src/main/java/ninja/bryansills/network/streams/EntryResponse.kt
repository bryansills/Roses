package ninja.bryansills.network.streams

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EntryResponse(
        val id: String,
        val title: String,
        val published: Long,
        val author: String,
        val canonicalUrl: String?
)
