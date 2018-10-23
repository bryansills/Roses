package ninja.bryansills.roses.inject

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ninja.bryansills.roses.category.CategoryActivity

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeCategoryActivity(): CategoryActivity

//    @ContributesAndroidInjector(modules = [RepoModule::class, ViewModelModule::class])
//    abstract fun contributeEntryActivity(): EntryActivity
}