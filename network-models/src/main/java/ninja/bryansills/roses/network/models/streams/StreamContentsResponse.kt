package ninja.bryansills.roses.network.models.streams

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StreamContentsResponse(
        val id: String,
        val updated: Long,
        val continuation: String,
        val items: Array<EntryResponse>
)