package ninja.bryansills.roses.entry

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

abstract class EntryViewModel : ViewModel() {
    abstract fun getEntries(categoryId: String): LiveData<EntryUiModel>
}