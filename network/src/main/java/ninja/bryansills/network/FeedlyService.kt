package ninja.bryansills.network

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers

interface FeedlyService {
    @Headers("Authorization: <snip>")
    @GET("profile")
    fun profile(): Observable<ProfileResponse>
}