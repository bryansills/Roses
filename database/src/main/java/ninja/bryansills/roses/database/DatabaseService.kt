package ninja.bryansills.roses.database

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import ninja.bryansills.network.models.streams.EntryResponse
import ninja.bryansills.roses.database.models.Category
import ninja.bryansills.roses.database.models.Entry

interface DatabaseService {
    fun getLastUpdated(): Single<Long>

    fun getEntries(categoryId: String): Flowable<List<Entry>>

    fun insertEntries(entries: List<EntryResponse>): Completable

    fun getCategories(): Observable<List<Category>>
}