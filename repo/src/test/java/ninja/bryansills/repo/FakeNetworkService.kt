package ninja.bryansills.repo

import io.reactivex.Observable
import ninja.bryansills.network.NetworkService
import ninja.bryansills.network.streams.StreamContentsResponse

class FakeNetworkService : NetworkService {

    var hasBeenCalled = false

    override fun streamContents(): Observable<StreamContentsResponse> {
        hasBeenCalled = true
        return Observable.just(StreamContentsResponse("TEST_ID", 1L, "TEST_CONTINUATION", emptyArray()))
    }
}