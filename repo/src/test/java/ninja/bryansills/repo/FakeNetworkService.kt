package ninja.bryansills.repo

import ninja.bryansills.roses.network.NetworkService
import ninja.bryansills.roses.network.models.streams.StreamContentsResponse
import ninja.bryansills.roses.network.test.NetworkTestUtils

class FakeNetworkService : NetworkService {

    var hasBeenCalled = false
    var emitError = false

    var contents = NetworkTestUtils.createStreamContentsResponse()

    override suspend fun streamContents(): StreamContentsResponse {
        hasBeenCalled = true
        return if (emitError) throw IllegalStateException("I am throwing an error") else contents
    }
}