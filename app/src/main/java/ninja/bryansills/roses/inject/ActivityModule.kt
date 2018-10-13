package ninja.bryansills.roses.inject

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ninja.bryansills.roses.category.CategoryActivity
import ninja.bryansills.roses.entry.EntryActivity
import javax.inject.Singleton

@Module
abstract class ActivityModule {
    @Singleton
    @ContributesAndroidInjector(modules = [RepoModule::class, ViewModelModule::class])
    abstract fun contributeCategoryActivity(): CategoryActivity

    @Singleton
    @ContributesAndroidInjector(modules = [RepoModule::class, ViewModelModule::class])
    abstract fun contributeEntryActivity(): EntryActivity
}