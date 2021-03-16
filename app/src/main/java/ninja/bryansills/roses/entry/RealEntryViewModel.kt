package ninja.bryansills.roses.entry

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ninja.bryansills.repo.FetchEntryResult
import ninja.bryansills.repo.Repository
import ninja.bryansills.roses.R
import ninja.bryansills.roses.coroutine.CoroutineDispatchers
import javax.inject.Inject

@HiltViewModel
class RealEntryViewModel @Inject constructor(
    private val repository: Repository,
    private val coroutineDispatchers: CoroutineDispatchers
) : EntryViewModel() {

    private val entries = MutableLiveData<EntryUiModel>()

    override fun getEntries(categoryId: String): LiveData<EntryUiModel> {
        viewModelScope.launch(coroutineDispatchers.UI) {
            entries.value = EntryUiModel.Loading()

            val resultingUiState = withContext(coroutineDispatchers.IO) {
                try {
                    when (val repoResult = repository.getEntries(categoryId)) {
                        is FetchEntryResult.InFlight -> EntryUiModel.Loading()
                        is FetchEntryResult.Error -> EntryUiModel.Error(
                            R.string.unknown_entry_error
                        )
                        is FetchEntryResult.Success -> EntryUiModel.Success(repoResult.entries)
                    }
                } catch (error: Exception) {
                    EntryUiModel.Error(R.string.unknown_entry_error)
                }
            }

            entries.value = resultingUiState
        }

        return entries
    }
}
