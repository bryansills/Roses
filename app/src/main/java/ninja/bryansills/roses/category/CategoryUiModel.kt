package ninja.bryansills.roses.category

import android.content.Context
import androidx.annotation.StringRes
import ninja.bryansills.repo.Category

sealed class CategoryUiModel {
    data class Success(val categories: List<Category>) : CategoryUiModel()
    object Loading : CategoryUiModel()
    data class Error(@StringRes val error: Int) : CategoryUiModel()

    fun isLoading(): Boolean {
        return when (this) {
            is Loading -> true
            else -> false
        }
    }

    fun hasError(): Boolean {
        return when (this) {
            is Error -> true
            else -> false
        }
    }

    fun isEmpty(): Boolean {
        return when (this) {
            is Success -> this.categories.isEmpty()
            else -> false
        }
    }

    fun hasData(): Boolean {
        return when (this) {
            is Success -> this.categories.isNotEmpty()
            else -> false
        }
    }

    fun getError(context: Context): String {
        return when (this) {
            is Error -> context.getString(this.error)
            else -> ""
        }
    }

    fun getData(): List<Category> {
        return when (this) {
            is Success -> this.categories
            else -> emptyList()
        }
    }
}