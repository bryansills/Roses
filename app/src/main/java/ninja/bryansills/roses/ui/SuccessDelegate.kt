package ninja.bryansills.roses.ui

import android.content.Context

class SuccessDelegate<S>(result: S) : AsyncUiModel<S> {
    override val loading = false
    override val hasError = false
    override val hasData = result != null
    override fun getError(context: Context) = null
    override val data = result
}