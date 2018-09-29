package ninja.bryansills.roses.inject

import android.content.Context
import dagger.Module
import dagger.Provides
import ninja.bryansills.roses.RosesApplication

@Module
class ApplicationModule {
    @Provides
    fun context(app: RosesApplication): Context = app.applicationContext
}