package ninja.bryansills.database

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import ninja.bryansills.database.models.Category
import ninja.bryansills.database.models.Entry
import ninja.bryansills.network.streams.EntryResponse

interface DatabaseService {
    fun getLastUpdated(): Single<Long>

    fun getEntries(categoryId: String): Flowable<List<Entry>>

    fun insertEntries(entries: List<EntryResponse>): Completable

    fun getCategories(): Observable<List<Category>>
}