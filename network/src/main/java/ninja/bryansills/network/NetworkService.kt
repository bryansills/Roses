package ninja.bryansills.network

import io.reactivex.Single
import ninja.bryansills.network.models.streams.StreamContentsResponse

interface NetworkService {
    fun streamContents(): Single<StreamContentsResponse>
}
