package ninja.bryansills.roses.category

import androidx.lifecycle.ViewModel
import io.reactivex.Flowable
import ninja.bryansills.repo.Category
import ninja.bryansills.repo.Repository
import javax.inject.Inject

class CategoryViewModel @Inject constructor(val repository: Repository) : ViewModel() {
    fun categories(): Flowable<List<Category>> {
        return repository.categories()
    }
}