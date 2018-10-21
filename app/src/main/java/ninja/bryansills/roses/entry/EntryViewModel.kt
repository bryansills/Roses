package ninja.bryansills.roses.entry

import androidx.lifecycle.ViewModel
import io.reactivex.Flowable
import ninja.bryansills.repo.Entry
import ninja.bryansills.repo.Repository
import javax.inject.Inject

class EntryViewModel @Inject constructor(val repository: Repository) : ViewModel() {
    fun getEntries(categoryId: String): Flowable<List<Entry>> {
        return repository.getEntries(categoryId)
    }
}