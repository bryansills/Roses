package ninja.bryansills.repo

import dagger.Module
import dagger.Provides
import ninja.bryansills.roses.database.DatabaseService
import ninja.bryansills.roses.network.NetworkService
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepoModule(
        val networkService: NetworkService,
        val databaseService: DatabaseService,
        @Named("REFRESH_INTERVAL") val refreshInterval: Int
) {
    @Provides
    @Singleton
    fun repo(
            networkService: NetworkService,
            databaseService: DatabaseService,
            @Named("REFRESH_INTERVAL") refreshInterval: Int
    ): Repository =
            RealRepository(networkService, databaseService, refreshInterval)
}