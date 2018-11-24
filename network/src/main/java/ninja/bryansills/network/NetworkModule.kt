package ninja.bryansills.network

import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun network(@Named("FEEDLY_ACCESS_TOKEN") feedlyAccessToken: String): NetworkService =
            NetworkService(feedlyAccessToken)
}