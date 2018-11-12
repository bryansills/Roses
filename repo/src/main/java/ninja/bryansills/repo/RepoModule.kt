package ninja.bryansills.repo

import android.content.Context
import dagger.Module
import dagger.Provides
import ninja.bryansills.database.DatabaseService
import ninja.bryansills.network.NetworkService
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepoModule {
    @Provides
    @Singleton
    fun database(context: Context): DatabaseService = DatabaseService(context)

    @Provides
    @Singleton
    fun network(@Named("FEEDLY_ACCESS_TOKEN") feedlyAccessToken: String): NetworkService =
            NetworkService(feedlyAccessToken)

    @Provides
    @Singleton
    fun repo(
            networkService: NetworkService,
            databaseService: DatabaseService,
            @Named("REFRESH_INTERVAL") refreshInterval: Int
    ): Repository =
            RealRepository(networkService, databaseService, refreshInterval)
}