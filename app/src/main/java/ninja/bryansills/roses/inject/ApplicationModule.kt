package ninja.bryansills.roses.inject

import android.content.Context
import dagger.Module
import dagger.Provides
import ninja.bryansills.background.WorkerModule
import ninja.bryansills.repo.RepoModule
import ninja.bryansills.roses.BuildConfig
import ninja.bryansills.roses.RosesApplication
import javax.inject.Named

@Module(includes = [ViewModelModule::class, WorkerModule::class, RepoModule::class, RxModule::class])
class ApplicationModule {
    @Provides
    fun context(app: RosesApplication): Context = app.applicationContext

    @Provides
    @Named("REFRESH_INTERVAL")
    fun provideRefreshInterval(): Int = 3

    @Provides
    @Named("FEEDLY_ACCESS_TOKEN")
    fun provideFeedlyAccessToken(): String = BuildConfig.FEEDLY_ACCESS_TOKEN
}