package ninja.bryansills.roses.inject

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ninja.bryansills.roses.category.CategoryActivity
import javax.inject.Singleton

@Module
abstract class ActivityModule {
    @Singleton
    @ContributesAndroidInjector(modules = [RepoModule::class, ViewModelModule::class])
    abstract fun contributeYourActivityInjector(): CategoryActivity
}