package ninja.bryansills.repo

import io.reactivex.Single
import ninja.bryansills.roses.network.NetworkService
import ninja.bryansills.roses.network.models.streams.StreamContentsResponse
import ninja.bryansills.roses.network.test.NetworkTestUtils

class FakeNetworkService : NetworkService {

    var hasBeenCalled = false
    var emitError = false

    var contents = NetworkTestUtils.createStreamContentsResponse()

    override fun streamContents(): Single<StreamContentsResponse> {
        hasBeenCalled = true
        return if (emitError) Single.error(RuntimeException("This is an error")) else Single.just(contents)
    }
}