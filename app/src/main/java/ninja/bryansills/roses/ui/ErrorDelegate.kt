package ninja.bryansills.roses.ui

import android.content.Context
import androidx.annotation.StringRes

class ErrorDelegate<E>(@StringRes val error: Int) : AsyncUiModel<E> {
    override val loading = false
    override val hasError = true
    override val hasData = false
    override fun getError(context: Context) = context.getString(error)
    override val data = null
}