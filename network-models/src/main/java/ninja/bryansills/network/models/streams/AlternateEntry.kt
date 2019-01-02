package ninja.bryansills.network.models.streams

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AlternateEntry(
        val href: String?,
        val type: String?
)