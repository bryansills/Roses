package ninja.bryansills.repo

import dagger.Module
import dagger.Provides
import ninja.bryansills.roses.database.DatabaseService
import ninja.bryansills.roses.network.NetworkService
import javax.inject.Named

@Module
class RepoModule(
        @Named("REFRESH_INTERVAL") val refreshInterval: Int
) {

    @Provides
    @Named("REFRESH_INTERVAL")
    fun refreshInterval(): Int = refreshInterval

    @Provides
    fun repo(
            networkService: NetworkService,
            databaseService: DatabaseService,
            @Named("REFRESH_INTERVAL") refreshInterval: Int
    ): Repository =
            RealRepository(networkService, databaseService, refreshInterval)
}