package ninja.bryansills.roses.network

import io.reactivex.Single
import ninja.bryansills.roses.network.models.streams.StreamContentsResponse

interface NetworkService {
    fun streamContents(): Single<StreamContentsResponse>
}
