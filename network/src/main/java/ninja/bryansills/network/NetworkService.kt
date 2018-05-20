package ninja.bryansills.network

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class NetworkService {
    private val feedly: FeedlyService
    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://cloud.feedly.com/v3/")
                .addConverterFactory(MoshiConverterFactory.create().asLenient())
                .build()
        feedly = retrofit.create(FeedlyService::class.java)
    }

    fun getProfile() {
        feedly.profile().enqueue(object: Callback<ProfileResponse> {
            override fun onResponse(call: Call<ProfileResponse>?, response: Response<ProfileResponse>?) {
                Log.d("BLARG", response?.body()?.toString())
            }

            override fun onFailure(call: Call<ProfileResponse>?, t: Throwable?) {
                Log.d("BLARG", t.toString())
            }
        })
    }
}