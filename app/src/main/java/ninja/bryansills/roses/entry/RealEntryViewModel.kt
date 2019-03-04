package ninja.bryansills.roses.entry

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.FlowableTransformer
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ninja.bryansills.repo.Entry
import ninja.bryansills.repo.FetchEntryResult
import ninja.bryansills.repo.Repository
import ninja.bryansills.roses.R
import ninja.bryansills.roses.utils.AsyncUiModel
import javax.inject.Inject

class RealEntryViewModel @Inject constructor(
        private val repository: Repository,
        private val observeOnScheduler: Scheduler
) : EntryViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val entries = MutableLiveData<AsyncUiModel<List<Entry>>>()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    override fun getEntries(categoryId: String): LiveData<AsyncUiModel<List<Entry>>> {
        compositeDisposable.add(repository.getEntries(categoryId)
                .compose(toEntryUiModel())
                .subscribeOn(Schedulers.io())
                .observeOn(observeOnScheduler)
                .onErrorReturn { AsyncUiModel.Error(R.string.unknown_entry_error) }
                .subscribe { entries.value = it }
        )

        return entries
    }

    private fun toEntryUiModel(): FlowableTransformer<in FetchEntryResult, out AsyncUiModel<List<Entry>>> {
        return FlowableTransformer { fetchEntryResult ->
            fetchEntryResult.map { mapAsyncUiModel(it) }
                .startWith(AsyncUiModel.Loading())
                .onErrorReturn { AsyncUiModel.Error(R.string.unknown_entry_error) }
        }
    }

    private fun mapAsyncUiModel(input: FetchEntryResult): AsyncUiModel<List<Entry>> {
        return when (input) {
            is FetchEntryResult.InFlight -> AsyncUiModel.Loading()
            is FetchEntryResult.Error -> AsyncUiModel.Error(R.string.unknown_entry_error)
            is FetchEntryResult.Success -> AsyncUiModel.Success(input.entries)
        }
    }
}