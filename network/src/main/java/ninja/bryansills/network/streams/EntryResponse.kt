package ninja.bryansills.network.streams

data class EntryResponse(
        val id: String,
        val title: String,
        val published: Long,
        val author: String,
        val canonicalUrl: String
)
