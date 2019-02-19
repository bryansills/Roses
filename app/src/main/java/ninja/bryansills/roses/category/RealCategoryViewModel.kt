package ninja.bryansills.roses.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ninja.bryansills.repo.FetchCategoryResult
import ninja.bryansills.repo.Repository
import javax.inject.Inject

class RealCategoryViewModel @Inject constructor(
        private val repository: Repository,
        private val compositeDisposable: CompositeDisposable
) : CategoryViewModel() {

    private val categories = MutableLiveData<CategoryUiModel>()

    init {
        compositeDisposable.add(repository.categories()
                .compose(toCategoryUiModel())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { categories.value = it }
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    override fun getCategories(): LiveData<CategoryUiModel> = categories

    private fun toCategoryUiModel(): ObservableTransformer<in FetchCategoryResult, out CategoryUiModel>? {
        return ObservableTransformer { fetchCategoryResult ->
            fetchCategoryResult.map { when (it) {
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