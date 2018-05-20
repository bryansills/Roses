package ninja.bryansills.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProfileResponse(
        val id: String,
        val email: String,
        val fullName: String
)
