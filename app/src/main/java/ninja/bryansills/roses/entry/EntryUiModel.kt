package ninja.bryansills.roses.entry

import androidx.annotation.StringRes
import ninja.bryansills.repo.Entry

sealed class EntryUiModel {
    data class Success(val entries: List<Entry>) : EntryUiModel()
    object Loading : EntryUiModel()
    data class Error(@StringRes val error: Int) : EntryUiModel()
}