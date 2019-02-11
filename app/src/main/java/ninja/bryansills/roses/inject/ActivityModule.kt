package ninja.bryansills.roses.inject

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import ninja.bryansills.roses.MainActivity

@Module(includes = [AndroidSupportInjectionModule::class])
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeMainActivity(): MainActivity
}