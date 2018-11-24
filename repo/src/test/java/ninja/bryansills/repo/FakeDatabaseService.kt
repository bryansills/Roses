package ninja.bryansills.repo

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import ninja.bryansills.database.Category
import ninja.bryansills.database.DatabaseService
import ninja.bryansills.database.Entry
import ninja.bryansills.network.streams.EntryResponse

class FakeDatabaseService : DatabaseService {
    override fun getEntries(categoryId: String): Flowable<List<Entry>> {
        return Flowable.just(emptyList())
    }

    override fun insertEntries(entries: List<EntryResponse>): Completable {
        return Completable.complete()
    }

    override fun getCategories(): Observable<List<Category>> {
        return Observable.just(emptyList())
    }

    override fun getLastUpdated(): Single<Long> {
        return Single.just(1L)
    }

}