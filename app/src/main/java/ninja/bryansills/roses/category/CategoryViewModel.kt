package ninja.bryansills.roses.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ninja.bryansills.repo.Category
import ninja.bryansills.repo.FetchCategoryResult
import ninja.bryansills.repo.Repository
import ninja.bryansills.roses.CategoryUIEvent
import javax.inject.Inject

class CategoryViewModel @Inject constructor(val repository: Repository,
                                            private val compositeDisposable: CompositeDisposable) : ViewModel() {

    private val _categoryLiveData = MutableLiveData<CategoryUiModel>()
    val categoriesLiveData: LiveData<CategoryUiModel> = _categoryLiveData

    private val _uiEventLiveData = MutableLiveData<CategoryUIEvent>()
    val uiEventLiveData: LiveData<CategoryUIEvent> = _uiEventLiveData

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun categories(): Observable<CategoryUiModel> {
        return repository.categories()
                .compose(toCategoryUiModel())
    }

    fun onClick(category: Category) {
        _uiEventLiveData.value = CategoryUIEvent.SetupNavigationController
    }

    fun initCategories() {
        val disposable = categories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            _categoryLiveData.value = it
                        },
                        {
//                            _categoryLiveData.value = it
                        }
                )
        compositeDisposable.add(disposable)
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