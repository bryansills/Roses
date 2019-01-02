package ninja.bryansills.network

import io.reactivex.Observable
import ninja.bryansills.network.models.streams.StreamContentsResponse

interface NetworkService {
    fun streamContents(): Observable<StreamContentsResponse>
}
