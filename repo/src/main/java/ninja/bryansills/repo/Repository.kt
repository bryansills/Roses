package ninja.bryansills.repo

import io.reactivex.Flowable

interface Repository {
    fun categories(): Flowable<List<Category>>

    fun getEntries(categoryId: String): Flowable<List<Entry>>
}