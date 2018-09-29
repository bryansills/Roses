package ninja.bryansills.roses

import androidx.lifecycle.ViewModel
import io.reactivex.Flowable
import ninja.bryansills.repo.Category
import ninja.bryansills.repo.Repository

class CategoryViewModel(val repository: Repository) : ViewModel() {
    fun categories(): Flowable<List<Category>> {
        return repository.categories()
    }
}