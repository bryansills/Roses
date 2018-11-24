package ninja.bryansills.network

import io.reactivex.Observable
import ninja.bryansills.network.streams.StreamContentsResponse
import javax.inject.Inject

class RealNetworkService @Inject constructor(val feedly: FeedlyService) : NetworkService {
    override fun streamContents(): Observable<StreamContentsResponse> {
        return feedly.profile().flatMap { response ->
            feedly.streamContents("user/${response.id}/category/global.all", 50)
        }
    }
}
