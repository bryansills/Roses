package ninja.bryansills.network.streams

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EntryResponse(
        val id: String,
        val title: String?,
        val published: Long,
        val author: String?,
        val canonicalUrl: String?,
        val originId: String,
        val alternate: List<AlternateEntry>?
) {
    val url: String?
        get() {
            val alternateUrl = alternate?.find { it.type == "text/html" }?.href
            return when {
                alternateUrl != null -> alternateUrl
                canonicalUrl != null -> canonicalUrl
                else -> null
            }
        }
}