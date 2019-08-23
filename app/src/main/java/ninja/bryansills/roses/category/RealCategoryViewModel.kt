package ninja.bryansills.roses.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ninja.bryansills.repo.FetchCategoryResult
import ninja.bryansills.repo.Repository
import ninja.bryansills.roses.R
import ninja.bryansills.roses.coroutine.CoroutineDispatchers
import javax.inject.Inject

class RealCategoryViewModel @Inject constructor(
        private val repository: Repository,
        private val coroutineDispatchers: CoroutineDispatchers
) : CategoryViewModel() {

    private val categories = MutableLiveData<CategoryUiModel>()

    init {
        viewModelScope.launch(coroutineDispatchers.UI) {
            categories.value = CategoryUiModel.Loading

            val resultingUiState = withContext(coroutineDispatchers.IO) {
                try {
                    when (val network = repository.categories()) {
                        is FetchCategoryResult.InFlight -> CategoryUiModel.Loading
                        is FetchCategoryResult.Success ->CategoryUiModel.Success(network.categories)
                        is FetchCategoryResult.Error -> toErrorMessage(network.error)
                    }
                } catch (error: Exception) {
                    CategoryUiModel.Error(R.string.unknown_category_error)
                }
            }

            categories.value = resultingUiState
        }
    }

    override fun getCategories(): LiveData<CategoryUiModel> = categories

    private fun toErrorMessage(error: FetchCategoryResult.FetchCategoryError): CategoryUiModel {
        return when (error) {
            FetchCategoryResult.FetchCategoryError.API_KEY_INVALID -> CategoryUiModel.Error(R.string.api_key_invalid)
            FetchCategoryResult.FetchCategoryError.RATE_LIMIT_REACHED -> CategoryUiModel.Error(R.string.api_rate_limit)
            else -> CategoryUiModel.Error(R.string.unknown_category_error)
        }
    }
}