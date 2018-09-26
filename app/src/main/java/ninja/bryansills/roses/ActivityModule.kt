package ninja.bryansills.roses

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ActivityScope
    @ContributesAndroidInjector()
    abstract fun contributeYourActivityInjector(): MainActivity
}