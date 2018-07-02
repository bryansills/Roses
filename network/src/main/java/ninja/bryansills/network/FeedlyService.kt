package ninja.bryansills.network

import io.reactivex.Observable
import retrofit2.http.GET

interface FeedlyService {
    @GET("profile")
    fun profile(): Observable<ProfileResponse>
}