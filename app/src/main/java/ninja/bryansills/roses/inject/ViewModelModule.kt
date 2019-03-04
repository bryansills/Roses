package ninja.bryansills.roses.inject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import ninja.bryansills.roses.category.CategoryViewModel
import ninja.bryansills.roses.category.RealCategoryViewModel
import ninja.bryansills.roses.entry.EntryViewModel
import ninja.bryansills.roses.entry.RealEntryViewModel
import ninja.bryansills.roses.factory.ViewModelFactory

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ClassKey(CategoryViewModel::class)
    abstract fun bindCategoryViewModel(categoryViewModel: RealCategoryViewModel): ViewModel

    @Binds
    @IntoMap
    @ClassKey(EntryViewModel::class)
    abstract fun bindEntryViewModel(entryViewModel: RealEntryViewModel): ViewModel

    @Binds
    abstract fun bindFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}