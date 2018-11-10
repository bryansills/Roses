package ninja.bryansills.roses.category

import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import ninja.bryansills.repo.Category
import ninja.bryansills.repo.Repository
import javax.inject.Inject

class CategoryViewModel @Inject constructor(val repository: Repository) : ViewModel() {
    fun categories(): Observable<CategoryUiModel> {
        return repository.categories()
                .compose(toCategoryUiModel())
    }

    private fun toCategoryUiModel(): ObservableTransformer<in List<Category>, out CategoryUiModel>? {
        return ObservableTransformer { it ->
            it.map { CategoryUiModel.Success(it) as CategoryUiModel }
                .startWith(CategoryUiModel.Loading)
                .onErrorReturn { CategoryUiModel.Error(it) }
        }
    }
}