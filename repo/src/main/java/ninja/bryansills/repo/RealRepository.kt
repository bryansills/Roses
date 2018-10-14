package ninja.bryansills.repo

import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import ninja.bryansills.database.DatabaseService
import ninja.bryansills.network.NetworkService
import java.util.Date

class RealRepository(var networkService: NetworkService, var databaseService: DatabaseService) : Repository {
    override fun categories(): Flowable<List<Category>> {
        networkService.streamContents()
                .subscribeOn(Schedulers.io())
                .subscribe { response -> databaseService.insertEntries(response.items.toList()) }

        return databaseService.categories().map { categories -> categories.map {
            return@map Category(it.id, it.title, it.count)
        } }
    }

    override fun getEntries(categoryId: String): Flowable<List<Entry>> {
        return databaseService.getEntries(categoryId).map {
            dbEntries -> dbEntries.map { Entry(it.id, it.title, it.url, Date(it.published), it.author) } }
    }
}