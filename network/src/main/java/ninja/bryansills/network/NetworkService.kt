package ninja.bryansills.network

import io.reactivex.Observable
import ninja.bryansills.network.streams.StreamContentsResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class NetworkService(feedlyAccessToken: String) {
    private val feedly: FeedlyService
    init {
        val interceptor = Interceptor { chain ->
            chain.proceed(chain.request()
                    .newBuilder()
                    .addHeader("Authorization", feedlyAccessToken)
                    .build())
        }
        val okHttp = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()
        val retrofit = Retrofit.Builder()
                .baseUrl("https://cloud.feedly.com/v3/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create().asLenient())
                .client(okHttp)
                .build()
        feedly = retrofit.create(FeedlyService::class.java)
    }

    fun getProfile(): Observable<ProfileResponse> {
        return feedly.profile()
    }

    fun streamContents(): Observable<StreamContentsResponse> {
        return feedly.profile().flatMap { response ->
            feedly.streamContents("user/${response.id}/category/global.all", 50)
        }
    }
}
