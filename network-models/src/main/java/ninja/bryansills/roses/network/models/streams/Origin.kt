package ninja.bryansills.roses.network.models.streams

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Origin(val streamId: String, val title: String, val htmlUrl: String)