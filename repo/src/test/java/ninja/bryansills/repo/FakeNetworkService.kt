package ninja.bryansills.repo

import io.reactivex.Observable
import ninja.bryansills.network.NetworkService
import ninja.bryansills.network.test.NetworkTestUtils
import ninja.bryansills.network.models.streams.StreamContentsResponse

class FakeNetworkService : NetworkService {

    var hasBeenCalled = false

    var contents = NetworkTestUtils.createStreamContentsResponse()

    override fun streamContents(): Observable<StreamContentsResponse> {
        hasBeenCalled = true
        return Observable.just(contents)
    }
}