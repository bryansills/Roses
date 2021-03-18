package ninja.bryansills.roses.category

import androidx.annotation.StringRes
import ninja.bryansills.roses.repo.Category
import ninja.bryansills.roses.ui.AsyncUiModel
import ninja.bryansills.roses.ui.ErrorDelegate
import ninja.bryansills.roses.ui.LoadingDelegate
import ninja.bryansills.roses.ui.SuccessDelegate

sealed class CategoryUiModel(
    delegate: AsyncUiModel<List<Category>>
) : AsyncUiModel<List<Category>> by delegate {

    data class Success(val categories: List<Category>) :
        CategoryUiModel(SuccessDelegate(categories)) {
        override val hasData = categories.isNotEmpty()
    }
    object Loading :
        CategoryUiModel(LoadingDelegate())
    data class Error(@StringRes val error: Int) :
        CategoryUiModel(ErrorDelegate(error))

    val isEmpty by lazy {
        when (this) {
            is Success -> this.data?.isEmpty()
            else -> false
        }
    }
}
