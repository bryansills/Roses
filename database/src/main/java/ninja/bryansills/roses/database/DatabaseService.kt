package ninja.bryansills.roses.database

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import ninja.bryansills.roses.database.models.Category
import ninja.bryansills.roses.database.models.Entry
import ninja.bryansills.roses.database.models.Origin

interface DatabaseService {
    fun getLastUpdated(): Single<Long>

    fun getEntries(categoryId: String): Flowable<List<Entry>>

    fun insertMappedEntries(entries: Map<Origin, List<Entry>>): Completable

    fun getCategories(): Observable<List<Category>>
}