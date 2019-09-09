package ninja.bryansills.roses.network

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import javax.inject.Named
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class NetworkModule(val token: String) {

    @Provides
    @Named("FEEDLY_ACCESS_TOKEN")
    fun token(): String = token

    @Provides
    fun network(feedlyService: FeedlyService): NetworkService =
        RealNetworkService(feedlyService)

    @Provides
    fun authInterceptor(@Named("FEEDLY_ACCESS_TOKEN") feedlyAccessToken: String): Interceptor =
        Interceptor { chain ->
            chain.proceed(
                chain.request()
                    .newBuilder()
                    .addHeader("Authorization", feedlyAccessToken)
                    .build()
            )
        }

    @Provides
    fun converterFactory(moshi: Moshi): Converter.Factory =
        MoshiConverterFactory.create(moshi)
            .asLenient()

    @Provides
    fun okhttp(interceptor: Interceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

    @Provides
    fun retrofit(converterFactory: Converter.Factory, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://cloud.feedly.com/v3/")
            .addConverterFactory(converterFactory)
            .client(okHttpClient)
            .build()

    @Provides
    fun feedlyService(retrofit: Retrofit): FeedlyService = retrofit.create(FeedlyService::class.java)
}