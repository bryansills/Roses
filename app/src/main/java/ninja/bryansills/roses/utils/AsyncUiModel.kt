package ninja.bryansills.roses.utils

import android.content.Context
import androidx.annotation.StringRes

// Move to Interface and Delegate?
sealed class AsyncUiModel<T> {
    open class Loading<T> : AsyncUiModel<T>()
    open class Error<Any>(@StringRes val error: Int) : AsyncUiModel<Any>()
    open class Success<T>(val result: T) : AsyncUiModel<T>()

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

    fun hasData(): Boolean {
        return when (this) {
            is Success -> this.result != null
            else -> false
        }
    }

    fun getError(context: Context): String {
        return when (this) {
            is Error -> context.getString(this.error)
            else -> ""
        }
    }

    fun getData(): T? {
        return when (this) {
            is Success -> this.result
            else -> null
        }
    }
}