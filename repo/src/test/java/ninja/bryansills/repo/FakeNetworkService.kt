package ninja.bryansills.repo

import io.reactivex.Observable
import ninja.bryansills.network.NetworkService
import ninja.bryansills.network.streams.StreamContentsResponse

class FakeNetworkService : NetworkService {
    override fun streamContents(): Observable<StreamContentsResponse> {
        return Observable.just(StreamContentsResponse("TEST_ID", 1L, "TEST_CONTINUATION", emptyArray()))
    }
}