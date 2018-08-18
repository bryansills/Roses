package ninja.bryansills.network.streams

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Origin(val streamId: String, val title: String, val htmlUrl: String)