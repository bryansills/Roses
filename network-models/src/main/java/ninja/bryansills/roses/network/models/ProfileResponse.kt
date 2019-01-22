package ninja.bryansills.roses.network.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProfileResponse(
        val id: String,
        val email: String,
        val fullName: String
)
