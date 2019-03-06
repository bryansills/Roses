package ninja.bryansills.roses.entry

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.FlowableTransformer
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ninja.bryansills.repo.FetchEntryResult
import ninja.bryansills.repo.Repository
import ninja.bryansills.roses.R
import javax.inject.Inject

class RealEntryViewModel @Inject constructor(
        private val repository: Repository,
        private val observeOnScheduler: Scheduler
) : EntryViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val entries = MutableLiveData<EntryUiModel>()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    override fun getEntries(categoryId: String): LiveData<EntryUiModel> {
        compositeDisposable.add(repository.getEntries(categoryId)
                .compose(toEntryUiModel())
                .subscribeOn(Schedulers.io())
                .observeOn(observeOnScheduler)
                .onErrorReturn { EntryUiModel.Error(R.string.unknown_entry_error) }
                .subscribe { entries.value = it }
        )

        return entries
    }

    private fun toEntryUiModel(): FlowableTransformer<in FetchEntryResult, out EntryUiModel> {
        return FlowableTransformer { fetchEntryResult ->
            fetchEntryResult.map { mapAsyncUiModel(it) }
                .startWith(EntryUiModel.Loading())
                .onErrorReturn { EntryUiModel.Error(R.string.unknown_entry_error) }
        }
    }

    private fun mapAsyncUiModel(input: FetchEntryResult): EntryUiModel {
        return when (input) {
            is FetchEntryResult.InFlight -> EntryUiModel.Loading()
            is FetchEntryResult.Error -> EntryUiModel.Error(R.string.unknown_entry_error)
            is FetchEntryResult.Success -> EntryUiModel.Success(input.entries)
        }
    }
}