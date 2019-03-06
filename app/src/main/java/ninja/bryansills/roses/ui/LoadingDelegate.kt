package ninja.bryansills.roses.ui

import android.content.Context

class LoadingDelegate<L> : AsyncUiModel<L> {
    override val loading = true
    override val hasError = false
    override val hasData = false
    override fun getError(context: Context) = null
    override val data = null
}