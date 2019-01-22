package ninja.bryansills.roses.network

import io.reactivex.Single
import ninja.bryansills.roses.network.models.ProfileResponse
import ninja.bryansills.roses.network.models.streams.StreamContentsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FeedlyService {
    @GET("profile")
    fun profile(): Single<ProfileResponse>

    @GET("streams/contents")
    fun streamContents(@Query("streamId") streamId: String, @Query("count") count: Int): Single<StreamContentsResponse>
}