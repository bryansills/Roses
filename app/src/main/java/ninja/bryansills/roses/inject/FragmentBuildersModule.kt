package ninja.bryansills.roses.inject

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ninja.bryansills.roses.category.CategoryFragment

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeRepoFragment(): CategoryFragment
}