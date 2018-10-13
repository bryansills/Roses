package ninja.bryansills.roses.inject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import ninja.bryansills.roses.CategoryViewModel
import ninja.bryansills.roses.ViewModelFactory

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ClassKey(CategoryViewModel::class)
    abstract fun bindCategoryViewModel(categoryViewModel: CategoryViewModel): ViewModel

    @Binds
    abstract fun bindFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}