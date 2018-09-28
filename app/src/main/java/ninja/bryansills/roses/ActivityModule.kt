package ninja.bryansills.roses

import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module
abstract class ActivityModule {
    @Singleton
    @ContributesAndroidInjector()
    abstract fun contributeYourActivityInjector(): MainActivity
}