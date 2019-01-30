package ninja.bryansills.repo

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import ninja.bryansills.database.test.DatabaseTestUtils
import ninja.bryansills.roses.database.DatabaseService
import ninja.bryansills.roses.database.models.Category
import ninja.bryansills.roses.database.models.Entry
import ninja.bryansills.roses.database.models.Origin
import java.util.Date

class FakeDatabaseService : DatabaseService {

    var hasCategoriesBeenCalled = false

    var lastUpdated = Date().time
    var categories = listOf<Category>()

    override fun getEntries(categoryId: String): Flowable<List<Entry>> {
        return Flowable.just(listOf(DatabaseTestUtils.createEntry(1, 1L, 1L)))
    }

    override fun insertMappedEntries(entries: Map<Origin, List<Entry>>): Completable {
        return Completable.complete()
    }

    override fun getCategories(): Observable<List<Category>> {
        hasCategoriesBeenCalled = true
        return Observable.just(categories)
    }

    override fun getLastUpdated(): Single<Long> {
        return Single.just(lastUpdated)
    }
}