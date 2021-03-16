package ninja.bryansills.roses.inject

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ninja.bryansills.repo.FetchCategoryResult
import ninja.bryansills.repo.FetchEntryResult
import ninja.bryansills.repo.Repository
import ninja.bryansills.roses.coroutine.CoroutineDispatchers
import ninja.bryansills.roses.coroutine.RealCoroutineDispatchers

// @Module(includes = [RepoModule::class])
// @InstallIn(SingletonComponent::class)
// class ApplicationModule {
//     @Provides
//     fun context(app: RosesApplication): Context = app.applicationContext
//
//     @Provides
//     @Named("REFRESH_INTERVAL")
//     fun provideRefreshInterval(): Int = 3
//
//     @Provides
//     @Named("FEEDLY_ACCESS_TOKEN")
//     fun provideFeedlyAccessToken(): String = BuildConfig.FEEDLY_ACCESS_TOKEN
//
//     @Provides
//     fun provideCoroutineDispatchers(): CoroutineDispatchers = RealCoroutineDispatchers()
// }

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {
    @Provides
    fun coroutineDispatchers(): CoroutineDispatchers = RealCoroutineDispatchers()

    @Provides
    fun repository(): Repository = FakeRepository()
}

class FakeRepository : Repository {
    override suspend fun categories(): FetchCategoryResult {
        return FetchCategoryResult.InFlight
    }

    override suspend fun getEntries(categoryId: String): FetchEntryResult {
        return FetchEntryResult.InFlight
    }

    override suspend fun updateDatabase() {
        Log.d("BLARG", "Updating Database")
    }
}
