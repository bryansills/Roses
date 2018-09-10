package ninja.bryansills.repo

import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import ninja.bryansills.database.DatabaseService
import ninja.bryansills.database.Entry
import ninja.bryansills.network.NetworkService

class DefaultRepository(var networkService: NetworkService, var databaseService: DatabaseService) : Repository {
    override fun entries(): Flowable<List<Entry>> {
        networkService.streamContents()
                .subscribeOn(Schedulers.io())
                .subscribe { response -> databaseService.insertEntries(response.items.asList()) }
        return databaseService.entries()
    }

}