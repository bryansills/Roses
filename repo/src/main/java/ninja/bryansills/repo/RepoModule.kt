package ninja.bryansills.repo

import dagger.Module
import dagger.Provides
import ninja.bryansills.roses.database.DatabaseModule
import ninja.bryansills.roses.database.DatabaseService
import ninja.bryansills.roses.network.NetworkModule
import ninja.bryansills.roses.network.NetworkService
import javax.inject.Named

@Module(includes = [NetworkModule::class, DatabaseModule::class])
object RepoModule {
    @Provides
    fun repo(
        networkService: NetworkService,
        databaseService: DatabaseService,
        @Named("REFRESH_INTERVAL") refreshInterval: Int
    ): Repository = RealRepository(networkService, databaseService, refreshInterval)
}
