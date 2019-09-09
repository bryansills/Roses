package ninja.bryansills.roses.inject

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Named
import ninja.bryansills.roses.BuildConfig
import ninja.bryansills.roses.RosesApplication

@Module(includes = [ViewModelModule::class, ActivityModule::class, CoroutineModule::class])
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