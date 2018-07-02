package ninja.bryansills.network

import android.util.Log
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class NetworkService {
    private val feedly: FeedlyService
    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://cloud.feedly.com/v3/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create().asLenient())
                .build()
        feedly = retrofit.create(FeedlyService::class.java)
    }

    fun getProfile() {
        feedly.profile().subscribe({
            Log.d("BLARG", it?.fullName)
        },
                {Log.d("BLARG", it.toString())}
        )



//                .enqueue(object: Callback<ProfileResponse> {
//            override fun onResponse(call: Call<ProfileResponse>?, response: Response<ProfileResponse>?) {
//            }
//
//            override fun onFailure(call: Call<ProfileResponse>?, t: Throwable?) {
//                Log.d("BLARG", t.toString())
//            }
//        })
    }
}
