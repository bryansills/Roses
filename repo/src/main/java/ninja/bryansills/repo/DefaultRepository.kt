package ninja.bryansills.repo

import io.reactivex.Observable
import ninja.bryansills.database.DatabaseService
import ninja.bryansills.database.Entry
import ninja.bryansills.network.NetworkService

class DefaultRepository(var networkService: NetworkService, var databaseService: DatabaseService) : Repository {
    override fun entries(): Observable<Entry> {
        return networkService.streamContents().map { _ -> Entry("this is not an id", "this is a title", "not a url") }
    }

}