package ninja.bryansills.roses.network

import io.reactivex.Single
import ninja.bryansills.roses.network.models.streams.StreamContentsResponse
import javax.inject.Inject

class RealNetworkService @Inject constructor(val feedly: FeedlyService) : NetworkService {
    override fun streamContents(): Single<StreamContentsResponse> {
        return feedly.profile().flatMap { response ->
            feedly.streamContents("user/${response.id}/category/global.all", 50)
        }
    }
}
