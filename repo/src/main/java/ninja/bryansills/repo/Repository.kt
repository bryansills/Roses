package ninja.bryansills.repo

import io.reactivex.Flowable
import ninja.bryansills.database.Entry

interface Repository {
    fun entries(): Flowable<List<Entry>>
}