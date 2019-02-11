package ninja.bryansills.roses.inject

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import ninja.bryansills.roses.category.CategoryFragment
import ninja.bryansills.roses.entry.EntryFragment
import ninja.bryansills.roses.factory.DaggerFragmentFactory
import ninja.bryansills.roses.navigation.RosesNavHostFragment

@Module
abstract class FragmentModule {
    @Binds
    @IntoMap
    @ClassKey(CategoryFragment::class)
    abstract fun bindCategoryFragment(categoryFragment: CategoryFragment): Fragment

    @Binds
    @IntoMap
    @ClassKey(EntryFragment::class)
    abstract fun bindEntryFragment(entryFragment: EntryFragment): Fragment

    @Binds
    @IntoMap
    @ClassKey(RosesNavHostFragment::class)
    abstract fun bindRosesNavHostFragment(rosesNavHostFragment: RosesNavHostFragment): Fragment

    @Binds
    abstract fun bindFactory(factory: DaggerFragmentFactory): FragmentFactory
}