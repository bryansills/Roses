package ninja.bryansills.network

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import ninja.bryansills.network.models.MoshiModule
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [MoshiModule::class])
class NetworkModule {
    @Provides
    @Singleton
    fun network(feedlyService: FeedlyService): NetworkService =
            RealNetworkService(feedlyService)

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
    fun converterFactory(moshi: Moshi): Converter.Factory =
            MoshiConverterFactory.create(moshi)
                    .asLenient()

    @Provides
    @Singleton
    fun okhttp(interceptor: Interceptor): OkHttpClient =
            OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

    @Provides
    @Singleton
    fun retrofit(converterFactory: Converter.Factory, okHttpClient: OkHttpClient): Retrofit =
            Retrofit.Builder()
                .baseUrl("https://cloud.feedly.com/v3/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(converterFactory)
                .client(okHttpClient)
                .build()

    @Provides
    @Singleton
    fun feedlyService(retrofit: Retrofit): FeedlyService = retrofit.create(FeedlyService::class.java)
}