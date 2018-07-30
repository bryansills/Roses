package ninja.bryansills.repo

import io.reactivex.Observable
import ninja.bryansills.database.Entry

interface Repository {
    fun entries(): Observable<Entry>
}