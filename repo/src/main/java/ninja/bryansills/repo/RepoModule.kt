package ninja.bryansills.repo

import dagger.Module
import dagger.Provides
import ninja.bryansills.roses.database.DatabaseModule
import ninja.bryansills.roses.database.DatabaseService
import ninja.bryansills.roses.network.NetworkModule
import ninja.bryansills.roses.network.NetworkService
import javax.inject.Named

@Module(includes = [NetworkModule::class, DatabaseModule::class])
class RepoModule(
    private val networkService: NetworkService,
    private val databaseService: DatabaseService,
    @Named("REFRESH_INTERVAL") private val refreshInterval: Int
) {
    @Provides
    fun repo(): Repository = RealRepository(networkService, databaseService, refreshInterval)
}
