package ninja.bryansills.roses.network

import ninja.bryansills.roses.network.models.ProfileResponse
import ninja.bryansills.roses.network.models.streams.StreamContentsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FeedlyService {
    @GET("profile")
    suspend fun profile(): ProfileResponse

    @GET("streams/contents")
    suspend fun streamContents(@Query("streamId") streamId: String, @Query("count") count: Int): StreamContentsResponse
}