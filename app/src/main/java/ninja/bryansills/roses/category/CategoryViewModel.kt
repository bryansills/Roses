package ninja.bryansills.roses.category

import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import ninja.bryansills.repo.FetchCategoryResult
import ninja.bryansills.repo.Repository
import javax.inject.Inject

class CategoryViewModel @Inject constructor(val repository: Repository) : ViewModel() {
    fun categories(): Observable<CategoryUiModel> {
        return repository.categories()
                .compose(toCategoryUiModel())
    }

    private fun toCategoryUiModel(): ObservableTransformer<in FetchCategoryResult, out CategoryUiModel>? {
        return ObservableTransformer { it ->
            it.map { when (it) {
                    is FetchCategoryResult.Success ->CategoryUiModel.Success(it.categories)
                    is FetchCategoryResult.Error -> toErrorMessage(it.error)
                } }
                .startWith(CategoryUiModel.Loading)
                .onErrorReturn { CategoryUiModel.Error("There has been an unknown error.") }
        }
    }

    private fun toErrorMessage(error: FetchCategoryResult.FetchCategoryError): CategoryUiModel {
        return when (error) {
            FetchCategoryResult.FetchCategoryError.API_KEY_INVALID -> CategoryUiModel.Error("API key is invalid. Please refresh it.")
            FetchCategoryResult.FetchCategoryError.RATE_LIMIT_REACHED -> CategoryUiModel.Error("API rate limit reached. Try again tomorrow.")
            else -> CategoryUiModel.Error("There has been an unknown error.")
        }
    }
}