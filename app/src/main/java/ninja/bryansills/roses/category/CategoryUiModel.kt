package ninja.bryansills.roses.category

import androidx.annotation.StringRes
import ninja.bryansills.repo.Category

sealed class CategoryUiModel {
    data class Success(val categories: List<Category>) : CategoryUiModel()
    object Loading : CategoryUiModel()
    data class Error(@StringRes val error: Int) : CategoryUiModel()
}