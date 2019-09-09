package ninja.bryansills.roses.network

import javax.inject.Inject
import ninja.bryansills.roses.network.models.streams.StreamContentsResponse

class RealNetworkService @Inject constructor(val feedly: FeedlyService) : NetworkService {
    override suspend fun streamContents(): StreamContentsResponse {
        val profile = feedly.profile()
        return feedly.streamContents("user/${profile.id}/category/global.all", 50)
    }
}
