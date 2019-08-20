package ninja.bryansills.roses.network

import ninja.bryansills.roses.network.models.streams.StreamContentsResponse
import javax.inject.Inject

class RealNetworkService @Inject constructor(val feedly: FeedlyService) : NetworkService {
    override suspend fun streamContents(): StreamContentsResponse {
        val profile = feedly.profile()
        return feedly.streamContents("user/${profile.id}/category/global.all", 50)
    }
}
