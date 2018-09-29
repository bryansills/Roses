package ninja.bryansills.roses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ninja.bryansills.repo.Repository
import javax.inject.Inject

class ViewModelFactory
    @Inject constructor(val repository: Repository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoryViewModel::class.java)) {
            return CategoryViewModel(repository) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}