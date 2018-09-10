package ninja.bryansills.repo

import io.reactivex.Flowable
import ninja.bryansills.database.Category

interface Repository {
    fun categories(): Flowable<List<Category>>
}