package ninja.bryansills.repo

import io.reactivex.Flowable
import io.reactivex.Single

interface Repository {
    fun categories(): Single<List<Category>>

    fun getEntries(categoryId: String): Flowable<List<Entry>>
}