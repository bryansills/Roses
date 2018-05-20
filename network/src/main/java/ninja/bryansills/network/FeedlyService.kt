package ninja.bryansills.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface FeedlyService {
    @Headers("Authorization: <snip>")
    @GET("profile")
    fun profile(): Call<ProfileResponse>
}