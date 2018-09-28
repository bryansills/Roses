package ninja.bryansills.roses

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {
    @Provides
    fun context(app: Application): Context = app.applicationContext
}