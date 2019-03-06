package ninja.bryansills.roses.ui

import android.content.Context

interface AsyncUiModel<T> {
    val loading: Boolean
    val hasError: Boolean
    val hasData: Boolean
    fun getError(context: Context): String?
    val data: T?
}