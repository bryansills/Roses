package ninja.bryansills.roses.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.ObservableTransformer
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ninja.bryansills.repo.FetchCategoryResult
import ninja.bryansills.repo.Repository
import ninja.bryansills.roses.R
import javax.inject.Inject

class RealCategoryViewModel @Inject constructor(
        private val repository: Repository,
        private val observeOnScheduler: Scheduler
) : CategoryViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val categories = MutableLiveData<CategoryUiModel>()

    init {
        compositeDisposable.add(repository.categories()
                .compose(toCategoryUiModel())
                .subscribeOn(Schedulers.io())
                .observeOn(observeOnScheduler)
                .onErrorReturn { CategoryUiModel.Error(R.string.unknown_error) }
                .subscribe { categories.value = it }
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    override fun getCategories(): LiveData<CategoryUiModel> = categories

    private fun toCategoryUiModel(): ObservableTransformer<in FetchCategoryResult, out CategoryUiModel> {
        return ObservableTransformer { fetchCategoryResult ->
            fetchCategoryResult.map { when (it) {
                    is FetchCategoryResult.InFlight -> CategoryUiModel.Loading
                    is FetchCategoryResult.Success ->CategoryUiModel.Success(it.categories)
                    is FetchCategoryResult.Error -> toErrorMessage(it.error)
                } }
                .startWith(CategoryUiModel.Loading)
                .onErrorReturn { CategoryUiModel.Error(R.string.unknown_error) }
        }
    }

    private fun toErrorMessage(error: FetchCategoryResult.FetchCategoryError): CategoryUiModel {
        return when (error) {
            FetchCategoryResult.FetchCategoryError.API_KEY_INVALID -> CategoryUiModel.Error(R.string.api_key_invalid)
            FetchCategoryResult.FetchCategoryError.RATE_LIMIT_REACHED -> CategoryUiModel.Error(R.string.api_rate_limit)
            else -> CategoryUiModel.Error(R.string.unknown_error)
        }
    }
}