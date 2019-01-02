package ninja.bryansills.repo

import io.reactivex.Observable
import ninja.bryansills.network.NetworkService
import ninja.bryansills.network.models.streams.StreamContentsResponse
import ninja.bryansills.network.test.NetworkTestUtils

class FakeNetworkService : NetworkService {

    var hasBeenCalled = false
    var emitError = false

    var contents = NetworkTestUtils.createStreamContentsResponse()

    override fun streamContents(): Observable<StreamContentsResponse> {
        hasBeenCalled = true
        return if (emitError) Observable.error(RuntimeException("This is an error")) else Observable.just(contents)
    }
}