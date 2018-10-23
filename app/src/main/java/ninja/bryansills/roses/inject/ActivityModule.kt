package ninja.bryansills.roses.inject

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ninja.bryansills.roses.MainActivity

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity
}