package ninja.bryansills.network

import io.reactivex.Observable
import ninja.bryansills.network.streams.StreamContentsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FeedlyService {
    @GET("profile")
    fun profile(): Observable<ProfileResponse>

    @GET("streams/contents")
    fun streamContents(@Query("streamId") streamId: String, @Query("count") count: Int): Observable<StreamContentsResponse>
}