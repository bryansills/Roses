package ninja.bryansills.network

import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun network(feedlyService: FeedlyService): NetworkService =
            NetworkService(feedlyService)

    @Provides
    @Singleton
    fun authInterceptor(@Named("FEEDLY_ACCESS_TOKEN") feedlyAccessToken: String): Interceptor =
            Interceptor { chain ->
                chain.proceed(chain.request()
                        .newBuilder()
                        .addHeader("Authorization", feedlyAccessToken)
                        .build())
            }

    @Provides
    @Singleton
    fun okhttp(interceptor: Interceptor): OkHttpClient =
            OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

    @Provides
    @Singleton
    fun retrofit(okHttpClient: OkHttpClient): Retrofit =
            Retrofit.Builder()
                .baseUrl("https://cloud.feedly.com/v3/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create().asLenient())
                .client(okHttpClient)
                .build()

    @Provides
    @Singleton
    fun feedlyService(retrofit: Retrofit): FeedlyService = retrofit.create(FeedlyService::class.java)
}