package ninja.bryansills.roses.category

import ninja.bryansills.repo.Category

sealed class CategoryUiModel {
    data class Success(val categories: List<Category>) : CategoryUiModel()
    object Loading : CategoryUiModel()
    data class Error(val error: String) : CategoryUiModel()
}