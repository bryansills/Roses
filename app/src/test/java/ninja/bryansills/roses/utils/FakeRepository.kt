package ninja.bryansills.roses.utils

import io.reactivex.BackpressureStrategy
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import ninja.bryansills.repo.FetchCategoryResult
import ninja.bryansills.repo.FetchEntryResult
import ninja.bryansills.repo.Repository

class FakeRepository : Repository {
    val categorySubject = PublishSubject.create<FetchCategoryResult>()
    val entriesSubject = BehaviorSubject.create<FetchEntryResult>()

    override fun categories(): Observable<FetchCategoryResult> = categorySubject
    override fun getEntries(categoryId: String): Flowable<FetchEntryResult> = entriesSubject.toFlowable(BackpressureStrategy.LATEST)
    override fun updateDatabase(): Completable = Completable.complete()
}