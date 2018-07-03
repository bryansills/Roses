package ninja.bryansills.network.streams

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StreamContentsResponse(
        val id: String,
        val updated: Long,
        val continuation: String,
        val items: Array<EntryResponse>
)