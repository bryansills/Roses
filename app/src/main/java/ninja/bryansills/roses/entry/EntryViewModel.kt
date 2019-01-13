package ninja.bryansills.roses.entry

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ninja.bryansills.repo.Entry
import ninja.bryansills.repo.Repository
import javax.inject.Inject

class EntryViewModel @Inject constructor(val repository: Repository,
                                         private val compositeDisposable: CompositeDisposable) : ViewModel() {

    private val _entries = MediatorLiveData<List<Entry>>()
    val entries: LiveData<List<Entry>> = _entries

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun initEntries(categoryId: String) {
        compositeDisposable.add(getEntries(categoryId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { _entries.value = it },
                        { Log.w("BLARG", it.toString()) }
                ))
    }

    fun getEntries(categoryId: String): Flowable<List<Entry>> {
        return repository.getEntries(categoryId)
    }
}