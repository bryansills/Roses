package ninja.bryansills.roses.entry

import androidx.annotation.StringRes
import ninja.bryansills.roses.repo.Entry
import ninja.bryansills.roses.ui.AsyncUiModel
import ninja.bryansills.roses.ui.ErrorDelegate
import ninja.bryansills.roses.ui.LoadingDelegate
import ninja.bryansills.roses.ui.SuccessDelegate

sealed class EntryUiModel(
    delegate: AsyncUiModel<List<Entry>>
) : AsyncUiModel<List<Entry>> by delegate {

    data class Success(val results: List<Entry>) :
        EntryUiModel(SuccessDelegate(results))
    class Loading :
        EntryUiModel(LoadingDelegate())
    data class Error(@StringRes val error: Int) :
        EntryUiModel(ErrorDelegate(error))
}
