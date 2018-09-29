package ninja.bryansills.roses.inject

import android.content.Context
import dagger.Module
import dagger.Provides
import ninja.bryansills.database.DatabaseService
import ninja.bryansills.network.NetworkService
import ninja.bryansills.repo.RealRepository
import ninja.bryansills.repo.Repository
import ninja.bryansills.roses.BuildConfig
import javax.inject.Singleton

@Module
class RepoModule {
    @Provides
    @Singleton
    fun database(context: Context): DatabaseService = DatabaseService(context)

    @Provides
    @Singleton
    fun network(): NetworkService =
            NetworkService(BuildConfig.FEEDLY_ACCESS_TOKEN)

    @Provides
    @Singleton
    fun repo(networkService: NetworkService, databaseService: DatabaseService): Repository =
            RealRepository(networkService, databaseService)
}