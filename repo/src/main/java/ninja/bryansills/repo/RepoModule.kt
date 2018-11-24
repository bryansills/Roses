package ninja.bryansills.repo

import dagger.Module
import dagger.Provides
import ninja.bryansills.database.DatabaseModule
import ninja.bryansills.database.DatabaseService
import ninja.bryansills.network.NetworkModule
import ninja.bryansills.network.NetworkService
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [DatabaseModule::class, NetworkModule::class])
class RepoModule {
    @Provides
    @Singleton
    fun repo(
            networkService: NetworkService,
            databaseService: DatabaseService,
            @Named("REFRESH_INTERVAL") refreshInterval: Int
    ): Repository =
            RealRepository(networkService, databaseService, refreshInterval)
}