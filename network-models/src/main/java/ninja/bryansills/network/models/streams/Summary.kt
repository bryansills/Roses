package ninja.bryansills.network.models.streams

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Summary(val content: String)