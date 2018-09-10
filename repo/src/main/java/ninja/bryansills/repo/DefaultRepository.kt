package ninja.bryansills.repo

import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import ninja.bryansills.database.Category
import ninja.bryansills.database.DatabaseService
import ninja.bryansills.network.NetworkService

class DefaultRepository(var networkService: NetworkService, var databaseService: DatabaseService) : Repository {
    override fun categories(): Flowable<List<Category>> {
        networkService.streamContents()
                .subscribeOn(Schedulers.io())
                .subscribe { response -> databaseService.insertEntries(response.items.asList()) }
        return databaseService.categories()
    }

}