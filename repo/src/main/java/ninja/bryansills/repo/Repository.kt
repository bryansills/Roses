package ninja.bryansills.repo

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable

interface Repository {
    fun categories(): Observable<FetchCategoryResult>

    fun getEntries(categoryId: String): Flowable<FetchEntryResult>

    fun updateDatabase(): Completable
}