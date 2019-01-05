package ninja.bryansills.network

import io.reactivex.Single
import ninja.bryansills.network.models.ProfileResponse
import ninja.bryansills.network.models.streams.StreamContentsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FeedlyService {
    @GET("profile")
    fun profile(): Single<ProfileResponse>

    @GET("streams/contents")
    fun streamContents(@Query("streamId") streamId: String, @Query("count") count: Int): Single<StreamContentsResponse>
}