package ninja.bryansills.roses.inject

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ninja.bryansills.roses.category.CategoryFragment
import ninja.bryansills.roses.entry.EntryFragment

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeCategoryFragment(): CategoryFragment

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeEntryFragment(): EntryFragment
}