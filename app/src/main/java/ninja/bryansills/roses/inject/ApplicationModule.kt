package ninja.bryansills.roses.inject

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ninja.bryansills.roses.repo.RepoModule
import ninja.bryansills.roses.BuildConfig
import ninja.bryansills.roses.coroutine.CoroutineDispatchers
import ninja.bryansills.roses.coroutine.RealCoroutineDispatchers
import javax.inject.Named

@Module(includes = [RepoModule::class])
@InstallIn(SingletonComponent::class)
object ApplicationModule {
    @Provides
    fun context(@ApplicationContext context: Context): Context = context

    @Provides
    @Named("REFRESH_INTERVAL")
    fun provideRefreshInterval(): Int = 3

    @Provides
    @Named("FEEDLY_ACCESS_TOKEN")
    fun provideFeedlyAccessToken(): String = BuildConfig.FEEDLY_ACCESS_TOKEN

    @Provides
    fun provideCoroutineDispatchers(): CoroutineDispatchers = RealCoroutineDispatchers()
}
