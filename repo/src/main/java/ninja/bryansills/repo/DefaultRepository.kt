package ninja.bryansills.repo

import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import ninja.bryansills.database.DatabaseService
import ninja.bryansills.database.Entry
import ninja.bryansills.network.NetworkService

class DefaultRepository(var networkService: NetworkService, var databaseService: DatabaseService) : Repository {
    override fun entries(): Flowable<List<Entry>> {
        networkService.streamContents().map { item -> item.items.map { entry -> Entry(entry.id, entry.title, entry.url) } }
                .subscribeOn(Schedulers.io())
                .subscribe { response -> databaseService.insertEntries(response) }
        return databaseService.entries()
    }

}