package ninja.bryansills.roses.entry

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ninja.bryansills.repo.Entry
import ninja.bryansills.roses.utils.AsyncUiModel

abstract class EntryViewModel : ViewModel() {
    abstract fun getEntries(categoryId: String): LiveData<AsyncUiModel<List<Entry>>>
}