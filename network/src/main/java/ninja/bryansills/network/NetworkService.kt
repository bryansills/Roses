package ninja.bryansills.network

import io.reactivex.Observable
import ninja.bryansills.network.streams.StreamContentsResponse
import javax.inject.Inject

class NetworkService @Inject constructor(val feedly: FeedlyService) {
    fun streamContents(): Observable<StreamContentsResponse> {
        return feedly.profile().flatMap { response ->
            feedly.streamContents("user/${response.id}/category/global.all", 50)
        }
    }
}
