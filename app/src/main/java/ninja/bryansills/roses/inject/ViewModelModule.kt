package ninja.bryansills.roses.inject

import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import ninja.bryansills.repo.Repository
import ninja.bryansills.roses.ViewModelFactory

@Module
class ViewModelModule {
    @Provides
    fun viewModelFactory(repository: Repository): ViewModelProvider.Factory = ViewModelFactory(repository)
}