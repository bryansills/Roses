package ninja.bryansills.repo

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import ninja.bryansills.database.test.DatabaseTestUtils
import ninja.bryansills.network.streams.EntryResponse
import ninja.bryansills.roses.database.DatabaseService
import ninja.bryansills.roses.database.models.Category
import ninja.bryansills.roses.database.models.Entry
import java.util.Date

class FakeDatabaseService : DatabaseService {

    var lastUpdated = Date().time
    var categories = listOf<Category>()

    override fun getEntries(categoryId: String): Flowable<List<Entry>> {
        return Flowable.just(listOf(DatabaseTestUtils.createEntry(1, 1L, 1L)))
    }

    override fun insertEntries(entries: List<EntryResponse>): Completable {
        return Completable.complete()
    }

    override fun getCategories(): Observable<List<Category>> {
        return Observable.just(categories)
    }

    override fun getLastUpdated(): Single<Long> {
        return Single.just(lastUpdated)
    }
}