package ninja.bryansills.roses.entry

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ninja.bryansills.repo.Entry
import ninja.bryansills.repo.Repository
import javax.inject.Inject

class EntryViewModel @Inject constructor(val repository: Repository,
                                         private val compositeDisposable: CompositeDisposable) : ViewModel() {

    private val entries = MutableLiveData<List<Entry>>()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun getEntries(categoryId: String): LiveData<List<Entry>> {
        compositeDisposable.add(repository.getEntries(categoryId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { entries.value = it },
                        { Log.w("BLARG", it.toString()) }
                ))

        return entries
    }
}